package com.excilys.cdb.exception;

/**
 * Encapsule les exceptions liees a sql ou jdbc afin de masquer les exceptions
 * specifique propres au DAO.
 * 
 * @author hhu
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/*
	 * Constructeurs
	 */
	public DAOException(String message) {
		super(message);
	}

}
