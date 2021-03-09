package com.excilys.cdb.dao;

/**
 * Encapsule les exceptions liees a sql ou jdbc afin de masquer les exceptions
 * specifique propres au DAO
 * 
 * @author hhu
 */
public class DAOConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public DAOConfigurationException(String message) {
		super(message);
	}

	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}
}