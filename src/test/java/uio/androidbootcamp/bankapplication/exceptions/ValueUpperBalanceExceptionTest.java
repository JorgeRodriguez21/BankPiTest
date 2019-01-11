package uio.androidbootcamp.bankapplication.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValueUpperBalanceExceptionTest {

    @Test
    public void shouldSetDescriptiveMessageWhenCreatingException(){
        String message = "Fondos insuficientes";

        ValueUpperBalanceException valueUpperBalanceException = new ValueUpperBalanceException();

        assertThat(message, is(valueUpperBalanceException.getMessage()));
    }

}