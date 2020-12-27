package com.capgemini.tlta.model;
public class Login {
   
   // @NotNull(message="email must not be empty")
    private Integer Id;
   // @NotEmpty(message="Password must not be empty")
    private String password;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}