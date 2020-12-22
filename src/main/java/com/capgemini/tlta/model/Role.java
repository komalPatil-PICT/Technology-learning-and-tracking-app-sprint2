package com.capgemini.tlta.model;

public enum Role {
	USER("user"),
	MODERATOR("moderator") ,
	ADMIN("admin");
	
private String role;
	
	private Role(String role) {
		this.role=role;
	}

	public String getRole() {
		return role;
	}
}
