package uio.androidbootcamp.bankapplication.exceptions;

public class NegativeValuesException extends Exception {

    public NegativeValuesException(){
        super("Ha ingresado numeros negativos, no es valido");
    }

}
