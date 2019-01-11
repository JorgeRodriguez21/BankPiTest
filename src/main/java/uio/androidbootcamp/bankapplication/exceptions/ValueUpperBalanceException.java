package uio.androidbootcamp.bankapplication.exceptions;

public class ValueUpperBalanceException extends Throwable {

    public ValueUpperBalanceException(){
        super("Fondos insuficientes");
    }
}
