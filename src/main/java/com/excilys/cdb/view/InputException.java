package com.excilys.cdb.view;

public class InputException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public InputException(String message) {
		super(message);
	}

	public InputException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputException(Throwable cause) {
		super(cause);
	}

}
