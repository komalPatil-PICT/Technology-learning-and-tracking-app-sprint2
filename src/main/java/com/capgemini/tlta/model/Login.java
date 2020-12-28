package com.capgemini.tlta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class Login.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer id;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	
}