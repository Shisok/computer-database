package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerDAO {
	void create(Computer computer) throws DAOException;

	void update(Computer computer) throws DAOException;

	void delete(Computer computer) throws DAOException;

	Computer search(Long id) throws DAOException;

	java.util.List<Computer> searchAll() throws DAOException;

	List<Computer> searchAllPagination(int page) throws DAOException;
}
