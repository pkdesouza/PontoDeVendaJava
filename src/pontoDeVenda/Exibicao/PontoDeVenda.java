package pontoDeVenda.Exibicao;

import pontoDeVenda.AcessoAosDados.DadosVenda;
import pontoDeVenda.AcessoAosDados.ObjetoAcessoAosDados;
import pontoDeVenda.AcessoAosDados.DadosProduto;
import pontoDeVenda.AcessoAosDados.DadosCliente;
import pontoDeVenda.AcessoAosDados.DadosLocalidade;
import pontoDeVenda.Modelos.Detalhamento;
import pontoDeVenda.Modelos.Produto;
import pontoDeVenda.Modelos.Localidade;
import pontoDeVenda.Modelos.Cliente;
import pontoDeVenda.Utilitarios.Formatador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PontoDeVenda extends javax.swing.JFrame {

    private ObjetoAcessoAosDados objetoTransferenciaDados;
    private Cliente cliente;
    private Localidade localidade;
    private Produto produto;
    private Produto produtoSelecionadoParaExclusao;
    private Detalhamento detalhamento;

    public PontoDeVenda() {
        initComponents();
        CarregarClientes();
        CarregarLocalidades();
        CarregarProdutos();
        ConfiguracaoTabela();
    }

    private void CarregarClientes() {
        objetoTransferenciaDados = new DadosCliente();
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = objetoTransferenciaDados.ObterTodosItens();
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, "Erro desconhecido, contate a T.I: \n" + ex.toString());
        }
        String nomes[] = new String[clientes.size() + 1];
        nomes[0] = "Selecione um";
        for (int i = 0; i < nomes.length - 1; i++) {
            nomes[i + 1] = clientes.get(i).obterNome() + " - " + clientes.get(i).obterCodigoCliente();
        }
        jComboBoxCliente.setModel(new DefaultComboBoxModel<>(nomes));
    }

    private void CarregarLocalidades() {
        try {
            objetoTransferenciaDados = new DadosLocalidade();
            List<Localidade> localidades = objetoTransferenciaDados.ObterTodosItens();
            String locais[] = new String[localidades.size() + 1];
            locais[0] = "Escolha algum valor";
            for (int i = 0; i < locais.length - 1; i++) {
                locais[i + 1] = localidades.get(i).getNome() + " | " + localidades.get(i).ObterIdentificadorLocalidade();
            }

            jComboBoxLocal.setModel(new DefaultComboBoxModel<>(locais));
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, "Erro desconhecido, contate a T.I: \n" + ex.toString());
        }

    }

    private void CarregarProdutos() {

        try {
            objetoTransferenciaDados = new DadosProduto();
            List<Produto> produtos  = objetoTransferenciaDados.ObterTodosItens();
            String codigos[] = new String[produtos.size() + 1];
            codigos[0] = "Selecione um";
            for (int i = 0; i < codigos.length - 1; i++)
                codigos[i + 1] = produtos.get(i).getCodProd().toString();
            jComboBoxProduto.setModel(new DefaultComboBoxModel<>(codigos));
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, "Erro desconhecido, contate a T.I: \n" + ex.toString());
        }

    }

    private void ConfiguracaoTabela() {
        jTableTabela.getColumnModel().getColumn(0).setMaxWidth(50);
        jTableTabela.getColumnModel().getColumn(1).setMinWidth(200);
        jTableTabela.getColumnModel().getColumn(2).setMaxWidth(80);
        jTableTabela.getColumnModel().getColumn(3).setMaxWidth(100);
        jTableTabela.getColumnModel().getColumn(4).setMaxWidth(100);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxLocal = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxProduto = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jButtonVender = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDesc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTabela = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalCompra = new javax.swing.JTextField();
        jButtonFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PDV - Ponto de Venda");
        setMinimumSize(new java.awt.Dimension(602, 667));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Caixa Livre");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Cliente Selecionado:");

        jComboBoxCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClienteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("Local de Venda:");

        jComboBoxLocal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxLocal.setEnabled(false);
        jComboBoxLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLocalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Código do Produto:");

        jComboBoxProduto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxProduto.setEnabled(false);
        jComboBoxProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProdutoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Quantidade:");

        jTextFieldQuantidade.setEditable(false);
        jTextFieldQuantidade.setText("0");
        jTextFieldQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQuantidadeKeyTyped(evt);
            }
        });

        jButtonVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pontoDeVenda/Recursos/ok.png"))); // NOI18N
        jButtonVender.setText("Vender");
        jButtonVender.setToolTipText("Preencha os dados antes de prosseguir!");
        jButtonVender.setEnabled(false);
        jButtonVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVenderActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pontoDeVenda/Recursos/close.png"))); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.setToolTipText("Selecione um item da tabela antes de excluir");
        jButtonExcluir.setEnabled(false);
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Descrição do produto");

        jTableTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "Quantidade", "Preço unitário", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableTabelaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTabela);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("Total de compra:");

        jTextFieldTotalCompra.setEditable(false);
        jTextFieldTotalCompra.setText("0");

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pontoDeVenda/Recursos/aperto.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDesc)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jTextFieldQuantidade))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonVender, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jButtonVender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExcluir)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFechar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeKeyTyped
        Formatador.formatarCampoNumerico(evt);
    }//GEN-LAST:event_jTextFieldQuantidadeKeyTyped

    private void jComboBoxProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProdutoActionPerformed
        Integer index = jComboBoxProduto.getSelectedIndex();

        if (index > 0) {
            jTextFieldQuantidade.setEditable(true);
            jButtonVender.setEnabled(true);
            jButtonVender.setToolTipText("");
        } else {
            jTextFieldQuantidade.setText("0");
            jTextFieldQuantidade.setEditable(false);
            jButtonVender.setEnabled(false);
            jButtonVender.setToolTipText("Preencha os dados antes de prosseguir!");
        }

        objetoTransferenciaDados = new DadosProduto();
        try {
            Integer codigo = Integer.parseInt(jComboBoxProduto.getSelectedItem().toString());
            produto = (Produto) objetoTransferenciaDados.ObterPeloIdentificador(codigo);
            jTextFieldDesc.setText(produto.getDescricao());
        } catch (NumberFormatException ex) {
            jTextFieldDesc.setText("");
            return;
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, "Erro desconhecido, contate a T.I: \n" + ex.toString());
        }
    }//GEN-LAST:event_jComboBoxProdutoActionPerformed

    private void jButtonVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVenderActionPerformed
        try {
            Long quantidade = Long.parseLong(jTextFieldQuantidade.getText());
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(null, "Quantidade insira uma quantidade igual ou maior que 1 (um)");
            } else {
                objetoTransferenciaDados = new DadosCliente();
                cliente = (Cliente) objetoTransferenciaDados.ObterPeloIdentificador(getCod(jComboBoxCliente.getSelectedItem().toString()));

                objetoTransferenciaDados = new DadosLocalidade();
                localidade = (Localidade) objetoTransferenciaDados.ObterPeloIdentificador(getCod(jComboBoxLocal.getSelectedItem().toString()));

                DadosVenda vendaDAO = new DadosVenda();

                detalhamento = vendaDAO.incluirVenda(cliente.obterCodigoCliente(), localidade.ObterIdentificadorLocalidade(),
                        produto.getCodProd(), quantidade);
                atualizarLista(false);
                atualizarTotal(detalhamento.getValorTotal(), false);

                limparCampos();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Preencha com números inteiros POSITIVOS o campo:\n QUANTIDADE");
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "\n" + ex.getCause().getMessage());
        }
    }//GEN-LAST:event_jButtonVenderActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        try {
            objetoTransferenciaDados = new DadosCliente();
            cliente = (Cliente) objetoTransferenciaDados.ObterPeloIdentificador(getCod(jComboBoxCliente.getSelectedItem().toString()));

            objetoTransferenciaDados = new DadosLocalidade();

            DadosVenda vendaDAO = new DadosVenda();

            vendaDAO.deletarVenda(cliente.obterCodigoCliente(), produtoSelecionadoParaExclusao.getCodProd(),
                    localidade.ObterIdentificadorLocalidade(), LocalDate.now());
            Double valor = atualizarLista(true);
            atualizarTotal(valor, true);
            jButtonExcluir.setEnabled(false);
            jButtonExcluir.setToolTipText("Selecione um item da tabela antes de excluir");
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Selecione um item da tabela antes de excluir");
        } catch (Throwable ex) {
            Logger.getLogger(PontoDeVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jTableTabelaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTabelaMouseReleased
        objetoTransferenciaDados = new DadosProduto();
        Integer row = jTableTabela.getSelectedRow();
        Integer id = (Integer) jTableTabela.getModel().getValueAt(row, 0);
        if (row == null) {
            jButtonExcluir.setEnabled(false);
            jButtonExcluir.setToolTipText("Selecione um item da tabela antes de excluir");
        } else {
            jButtonExcluir.setEnabled(true);
            jButtonExcluir.setToolTipText("");
        }
        try {
            produtoSelecionadoParaExclusao = (Produto) objetoTransferenciaDados.ObterPeloIdentificador(id);
        } catch (Throwable ex) {
            Logger.getLogger(PontoDeVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableTabelaMouseReleased

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        JOptionPane.showMessageDialog(null, "Obrigado professor Jorge Doria Junior pela transmissão de conhecimento");
        this.dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jComboBoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClienteActionPerformed
        Integer index = jComboBoxCliente.getSelectedIndex();

        if (index > 0) {
            jComboBoxLocal.setEnabled(true);
        } else {
            jComboBoxLocal.setSelectedIndex(0);
            jComboBoxLocal.setEnabled(false);
            limparLista();
        }
    }//GEN-LAST:event_jComboBoxClienteActionPerformed

    private void jComboBoxLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLocalActionPerformed
        Integer index = jComboBoxLocal.getSelectedIndex();

        if (index > 0) {
            jComboBoxProduto.setEnabled(true);
        } else {
            jComboBoxProduto.setSelectedIndex(0);
            jComboBoxProduto.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxLocalActionPerformed

    private void limparCampos() {
        produto = null;
        jTextFieldDesc.setText("");
        jTextFieldQuantidade.setText("");
        jComboBoxProduto.setSelectedIndex(0);
        produtoSelecionadoParaExclusao = null;
        detalhamento = null;

    }

    private Double atualizarLista(boolean isRemocao) {
        DefaultTableModel model = (DefaultTableModel) jTableTabela.getModel();
        if (isRemocao) {
            Double valor = (Double) jTableTabela.getValueAt(jTableTabela.getSelectedRow(), 4);
            model.removeRow(jTableTabela.getSelectedRow());
            return valor;
        } else {
            model.addRow(new Object[]{
                produto.getCodProd(),
                detalhamento.getDescricao(),
                detalhamento.getQteVenda(),
                detalhamento.getPrecoUnitario(),
                detalhamento.getValorTotal()
            });
            return 0.0;
        }

    }

    private void limparLista() {
        DefaultTableModel model = (DefaultTableModel) jTableTabela.getModel();
        Integer quantRow = model.getRowCount();
        model.setRowCount(0);
        /* for (int i = 1; i <= quantRow; i++) {
         System.out.println("passada: " + i);
         model.removeRow(i);
         }*/
    }

    private void atualizarTotal(Double valor, Boolean isRemocao) {
        if (isRemocao) {
            valor = Double.parseDouble(jTextFieldTotalCompra.getText()) - valor;
            jTextFieldTotalCompra.setText(valor.toString());
        } else {
            valor += Double.parseDouble(jTextFieldTotalCompra.getText());
            jTextFieldTotalCompra.setText(valor.toString());
        }
    }

    private Integer getCod(String txt) {
        return Integer.parseInt(txt.substring(txt.indexOf("-") + 2));
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PontoDeVenda().setVisible(true);
            }
        });
    }

//<editor-fold defaultstate="collapsed" desc=" Declaracao de variaveis - nao mexer ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonVender;
    private javax.swing.JComboBox<String> jComboBoxCliente;
    private javax.swing.JComboBox<String> jComboBoxLocal;
    private javax.swing.JComboBox<String> jComboBoxProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTabela;
    private javax.swing.JTextField jTextFieldDesc;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldTotalCompra;
    // End of variables declaration//GEN-END:variables
//</editor-fold>

}
