package uio.androidbootcamp.bankapplication.exceptions;

public class ValueUpper1000Exception extends Throwable {

    public  ValueUpper1000Exception(){
        super("El valor no puede ser mayor a mil dolares para este tipo de cuenta.");
    }
}
