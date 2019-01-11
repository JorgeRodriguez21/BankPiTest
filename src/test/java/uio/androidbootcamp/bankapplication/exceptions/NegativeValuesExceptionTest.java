package uio.androidbootcamp.bankapplication.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NegativeValuesExceptionTest {

    @Test
    public void testShouldSetDescriptiveMessageWhenCreateException(){
        String message = "Ha ingresado numeros negativos, no es valido";

        NegativeValuesException negativeValuesException = new NegativeValuesException();

        assertThat(message, is(negativeValuesException.getMessage()));
    }


}