package com.capgemini.tlta.sevice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserChangePasswordDO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String password;
	
}
