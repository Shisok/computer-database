package com.excilys.cdb.exception;

public class ValidatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorException(Throwable cause) {
		super(cause);
	}
}
