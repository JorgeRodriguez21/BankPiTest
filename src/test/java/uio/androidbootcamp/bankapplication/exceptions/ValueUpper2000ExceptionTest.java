package uio.androidbootcamp.bankapplication.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValueUpper2000ExceptionTest {

    @Test
    public void testShouldSetDescriptiveMessageWhenCreateValueUpper2000(){
        String message = "No puede ingresar valores mayores a 2000.";

        ValueUpper2000Exception valueUpper2000Exception = new ValueUpper2000Exception();

        assertThat(message, is(valueUpper2000Exception.getMessage()));
    }

}