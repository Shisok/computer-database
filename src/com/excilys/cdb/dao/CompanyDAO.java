package com.excilys.cdb.dao;

import com.excilys.cdb.model.Company;

public interface CompanyDAO {

	java.util.List<Company> searchAll() throws DAOException;

}
