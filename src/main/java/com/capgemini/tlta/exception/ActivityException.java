package com.capgemini.tlta.exception;

/**
 * The Class ActivityException.
 */
public class ActivityException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Instantiates a new activity exception.
	 */
	public ActivityException() {
		
	}

	/**
	 * Instantiates a new activity exception.
	 *
	 * @param message the message
	 */
	public ActivityException(String message) {
		super(message);
		this.message=message;
	}
	
	/**
	 * Instantiates a new activity exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public ActivityException(String message,Exception e) {
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
		return "ActivityException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
