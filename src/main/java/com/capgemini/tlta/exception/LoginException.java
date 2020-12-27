package com.capgemini.tlta.exception;
public class LoginException extends Exception {
	private String message;



	public LoginException() {

	}



	public LoginException(String message) {
		super(message);
		this.message=message;
	}

	public LoginException(String message,Exception e) {
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