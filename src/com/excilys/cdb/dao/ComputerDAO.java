package com.excilys.cdb.dao;

import com.excilys.cdb.model.Computer;

public interface ComputerDAO {
	void create(Computer computer) throws DAOException;

	void update(Computer computer) throws DAOException;

	void delete(Computer computer) throws DAOException;

	Computer search(Long id) throws DAOException;

}
