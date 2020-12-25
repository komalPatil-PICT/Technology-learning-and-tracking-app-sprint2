package com.capgemini.tlta.exception;

/**
 * The Class RegisterUserException.
 */
public class RegisterUserException extends Exception{
private static final long serialVersionUID = 1L;
	
	private String message;

	/**
	 * Instantiates a new register user exception.
	 */
	public RegisterUserException() {
		
	}

	/**
	 * Instantiates a new register user exception.
	 *
	 * @param message the message
	 */
	public RegisterUserException(String message) {
		super(message);
		this.message=message;
	}
	
	/**
	 * Instantiates a new register user exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public RegisterUserException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "LoginException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
