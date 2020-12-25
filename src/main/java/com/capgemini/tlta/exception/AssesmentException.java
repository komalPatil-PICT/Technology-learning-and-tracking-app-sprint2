package com.capgemini.tlta.exception;

/**
 * The Class AssesmentException.
 */
public class AssesmentException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

	/**
	 * Instantiates a new assesment exception.
	 */
	public AssesmentException() {
		
	}

	/**
	 * Instantiates a new assesment exception.
	 *
	 * @param message the message
	 */
	public AssesmentException(String message) {
		super(message);
		this.message=message;
	}
	
	/**
	 * Instantiates a new assesment exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public AssesmentException(String message,Exception e) {
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
		return "AssessmentException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
