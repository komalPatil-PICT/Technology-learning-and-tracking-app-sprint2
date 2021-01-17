package com.capgemini.tlta.exception;

/**
 * The Class LoginException.
 */
public class LoginException extends Exception {
	private String message;

	/**
	 * Instantiates a new login exception.
	 */
	public LoginException() {

	}

	/**
	 * Instantiates a new login exception.
	 *
	 * @param message the message
	 */
	public LoginException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Instantiates a new login exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public LoginException(String message, Exception e) {
		super(message, e);
		this.message = message;
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