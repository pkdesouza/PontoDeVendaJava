
package pontoDeVenda.TratamentoDeExcecoes;

public class ProdutoInexistenteException extends Exception{
    
    public ProdutoInexistenteException(String msg, Throwable cause){
        super(msg, cause);
    }
}
