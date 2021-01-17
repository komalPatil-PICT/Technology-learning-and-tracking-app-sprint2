package com.capgemini.tlta.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;
import com.capgemini.tlta.model.LogOutPayload;
import com.capgemini.tlta.model.Login;

/**
 * The Class LoginTests.
 */
class LoginTests {
    
    
    /**
     * Test sign in.
     */
    @Test
    public void testSignIn() {
        Login user =mock(Login.class);//mock(Login.class);
        when(user.getEmail()).thenReturn("abc@01");
        assertEquals(user.getEmail(),"abc@01");
        when(user.getPassword()).thenReturn("abc@01");
        assertEquals(user.getPassword(),"abc@01");
    
    }
    
      /**
       * Test sign out.
       */
      @Test
      void testSignOut() { 
         LogOutPayload user = mock(LogOutPayload.class);
     when(user.getEmail()).thenReturn("Saloni@g.com");
     assertEquals(user.getEmail(),"Saloni@g.com");
    }
}