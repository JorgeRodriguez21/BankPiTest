package uio.androidbootcamp.bankapplication.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValueUpper1000ExceptionTest {


    @Test
    public void testShouldCreateExceptionWithDescriptiveMessage(){
        String message = "El valor no puede ser mayor a mil dolares para este tipo de cuenta.";

        ValueUpper1000Exception valueUpper1000Exception = new ValueUpper1000Exception();

        assertThat(message, is(valueUpper1000Exception.getMessage()));
    }

}