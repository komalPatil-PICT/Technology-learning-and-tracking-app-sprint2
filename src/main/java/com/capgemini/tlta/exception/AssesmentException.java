package com.capgemini.tlta.exception;

public class AssesmentException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

	public AssesmentException() {
		
	}

	public AssesmentException(String message) {
		super(message);
		this.message=message;
	}
	
	public AssesmentException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "ProductException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
	
}
