package com.capgemini.tlta.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Login.
 */
@Getter
@Setter
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer Id;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	
}