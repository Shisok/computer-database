package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDAO {

	java.util.List<Company> searchAll() throws DAOException;

	List<Company> searchAllPagination(int page) throws DAOException;
}
