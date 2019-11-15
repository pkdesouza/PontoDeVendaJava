package pontoDeVenda.TratamentoDeExcecoes;

public class OutOFStockException extends Exception {

    public OutOFStockException(String message) {
        super(message);
    }

    public OutOFStockException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
