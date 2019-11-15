package pontoDeVenda.AcessoAosDados;

import pontoDeVenda.TratamentoDeExcecoes.ProdutoInexistenteException;
import pontoDeVenda.TratamentoDeExcecoes.OutOFStockException;
import pontoDeVenda.Modelos.Detalhamento;
import pontoDeVenda.Modelos.Produto;
import pontoDeVenda.Modelos.Localidade;
import pontoDeVenda.Modelos.Cliente;
import pontoDeVenda.Modelos.Venda;
import pontoDeVenda.BaseDeDados.Factory;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VendaDAO implements ObjetoAcessoAosDados<Venda> {

    private Connection con;
    private ObjetoAcessoAosDados dao;

    @Override
    public Venda ObterPeloIdentificador(Integer id) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> ObterTodosItens() throws OutOFStockException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Detalhamento incluirVenda(Integer codCli, Integer codLocal, Integer codProd, Long quantidade) throws SQLException, OutOFStockException, Throwable {
        try {
            con = Factory.getConnection();
            con.setAutoCommit(false);
            /*Pegando os dados do produto*/
            dao = new DadosProduto();
            Produto produto = (Produto) dao.ObterPeloIdentificador(codProd);

            /*Tratamento de inexistencia*/
            if (produto == null) {
                throw new ProdutoInexistenteException("Produto inexistente!", new NullPointerException());
            }
            /*Tratamento de estoque*/
            if (produto.getQteEstoque() < quantidade) {
                throw new OutOFStockException("Quantidade acima do que temos em estoque", new Throwable("Produtos em estoque: " + produto.getQteEstoque()));

            }

            /*Pegando os dados do cliente*/
            dao = new ClienteDAO();
            Cliente cliente = (Cliente) dao.ObterPeloIdentificador(codCli);

            /*Pegando os dados do local*/
            dao = new DadosLocalidade();
            Localidade local = (Localidade) dao.ObterPeloIdentificador(codLocal);

            String query = "UPDATE Produto SET qte_estoque = qte_estoque - ? WHERE codProd = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, quantidade);
            ps.setInt(2, codProd);
            ps.executeUpdate();

            Double total = calcularDesconto(codProd, codCli, quantidade, (quantidade * produto.getPrecoUnitario()));
            if (produto.getLocal().equals(local)) {
                total -= (total * 10 / 100);
            }
            Venda venda = new Venda(cliente, local, produto, quantidade, total, LocalDate.now());

            query = "INSERT INTO venda VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, cliente.getCodCli());
            ps.setInt(2, local.ObterIdentificadorLocalidade());
            ps.setInt(3, produto.getCodProd());
            ps.setLong(4, quantidade);
            ps.setDouble(5, total);
            ps.setDate(6, Date.valueOf(venda.getDataVenda()));

            Integer result = ps.executeUpdate();

            if (result == 1) {
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
            }
            query = "SELECT p.descricao, v.Qte_venda, p.preco_unitario, v.valor_total \n"
                    + "From Venda v\n"
                    + "JOIN Produto p ON p.Codprod = v.Codprod\n"
                    + "JOIN cliente c ON c.CodCli = v.codcli\n"
                    + "WHERE c.codCli = ? AND p.codprod = ?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, codCli);
            ps.setInt(2, codProd);
            ResultSet rs = ps.executeQuery();
            con.commit();
            rs.first();
            return new Detalhamento(rs.getString("descricao"), rs.getLong("Qte_venda"),
                    rs.getDouble("preco_unitario"), rs.getDouble("valor_total"));
        } catch (Throwable ex) {
            //Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            con.rollback();
            throw new Throwable(ex.getMessage(), ex.getCause());
        }
    }

    private Double calcularDesconto(Integer codProd, Integer codCli, Long quantidade, Double total) throws SQLException {
        Double aux = total;
        try {
            con = Factory.getConnection();
            con.setAutoCommit(false);

            String query = "SELECT bonus FROM cliente WHERE codcli = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCli);
            ResultSet rs = ps.executeQuery();
            rs.first();

            Long bonus = rs.getLong(1);
            if (bonus >= 100) {
                query = "SELECT percentual FROM desconto WHERE codProd = ? AND ? >= qtd_min AND ? <= qtd_max";
                ps = con.prepareStatement(query);
                ps.setInt(1, codProd);
                ps.setLong(2, quantidade);
                ps.setLong(3, quantidade);
                rs = ps.executeQuery();
                rs.beforeFirst();

                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Não há desconto disponível!", "Sem desconto", JOptionPane.INFORMATION_MESSAGE);
                    return aux;
                }

                Integer percentual = rs.getInt(1);

                total = total - (total * percentual / 100);
                query = "UPDATE cliente SET bonus = bonus - 100 WHERE codcli = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, codCli);
                ps.executeUpdate();
                con.commit();
                return total;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente sem pontos suficiente" + "\n" + "Bónus do cliente: " + bonus.toString());
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            con.rollback();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            con.rollback();
        }

        return aux;
    }

    public void deletarVenda(Integer codCli, Integer codProd, Integer codLocal, LocalDate data) throws SQLException {
        try {
            con = Factory.getConnection();
            con.setAutoCommit(false);

            /*Deletar venda*/
            String query = "SELECT valor_total, qte_venda, codlocal FROM venda WHERE codcli = ? AND codlocal = ?  AND data_venda = ? AND codprod = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codCli);
            ps.setInt(2, codLocal);
            ps.setDate(3, Date.valueOf(data));
            ps.setDouble(4, codProd);
            ResultSet rs = ps.executeQuery();
            rs.beforeFirst();
            if (!rs.next()) {
                con.rollback();
                throw new SQLException("Venda inexistente!");
            }
            rs.first();
            Long vqtde = rs.getLong("qte_venda");
            Long vLocalVenda = rs.getLong("codlocal");
            Double vtot = rs.getDouble("valor_total");

            /*Tratar estoque*/
            query = "Update produto SET qte_estoque = qte_estoque + ? Where codprod = ?";
            ps = con.prepareStatement(query);
            ps.setLong(1, vqtde);
            ps.setInt(2, codProd);
            Integer status = ps.executeUpdate();
            if (status != 1) {
                //erro
            }

            /*Devolver bonus*/
            query = "SELECT preco_unitario, codlocal FROM produto WHERE codprod = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, codProd);
            rs = ps.executeQuery();
            rs.first();
            Double valorTotalReal = rs.getDouble("preco_unitario") * vqtde;
            Long vLocalProd = rs.getLong("codlocal");
            Double vlrDesLocal = 0.0;
            if (vLocalProd == vLocalVenda) {
                vlrDesLocal = valorTotalReal * 0.10;
            }
            vtot += vlrDesLocal;

            query = "UPDATE cliente SET bonus = bonus + 100  Where codcli = ? AND ? < ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, codCli);
            ps.setDouble(2, vtot);
            ps.setDouble(3, valorTotalReal);
            ps.executeUpdate();

            /*Deletar venda*/
            query = "DELETE FROM venda WHERE codcli = ? AND data_venda = ? AND codprod = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, codCli);
            ps.setDate(2, Date.valueOf(data));
            ps.setInt(3, codProd);
            Integer result = ps.executeUpdate();

            if (result == 0) {
                con.rollback();
                throw new SQLException("Venda não excluída!");
            }
            con.commit();
            JOptionPane.showMessageDialog(null, "Venda Excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            con.rollback();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            con.rollback();
        }

    }

}
