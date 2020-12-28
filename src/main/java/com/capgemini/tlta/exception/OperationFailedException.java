package com.capgemini.tlta.exception;

/**
 * The Class OperationFailedException.
 */
public class OperationFailedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new operation failed exception.
	 *
	 * @param name the name
	 */
	public OperationFailedException(String name) {
		super(name);
	}
}