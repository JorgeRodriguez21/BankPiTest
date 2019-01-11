package uio.androidbootcamp.bankapplication.exceptions;

public class ValueUpper2000Exception extends Throwable {

        public ValueUpper2000Exception(){
            super("No puede ingresar valores mayores a 2000.");
        }
}
