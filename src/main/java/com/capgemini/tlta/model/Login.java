package com.capgemini.tlta.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The Class Login.
 */

@AllArgsConstructor
@NoArgsConstructor
public class Login {
	// @NotNull(message="email must not be empty")
    private String email;
   // @NotEmpty(message="Password must not be empty")
    private String password;
    
    private Role role; 
  
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	
}