package com.capgemini.tlta.exception;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

 

import org.junit.jupiter.api.Test;

 

import com.capgemini.tlta.exception.OperationFailedException;

 

/**
 * The Class LoginExceptionTest.
 */
public class LoginExceptionTest {
    
    /**
     * When exception thrown then assertion succeeds.
     */
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });
        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

 

    /**
     * Exception testing.
     */
    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(OperationFailedException.class, () -> {
            throw new OperationFailedException ("a message");
        });
        assertEquals("a message", exception.getMessage());
    }
    
    
}