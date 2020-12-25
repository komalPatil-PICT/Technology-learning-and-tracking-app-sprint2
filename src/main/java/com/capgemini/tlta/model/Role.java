package com.capgemini.tlta.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The Enum Role.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {
	USER("user"), MODERATOR("moderator"), ADMIN("admin");
	
	@Getter
	private String role;
}
