package com.capgemini.tlta.exception;

public class RegisterUserException extends Exception{
private static final long serialVersionUID = 1L;
	
	private String message;

	public RegisterUserException() {
		
	}

	public RegisterUserException(String message) {
		super(message);
		this.message=message;
	}
	
	public RegisterUserException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "LoginException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
