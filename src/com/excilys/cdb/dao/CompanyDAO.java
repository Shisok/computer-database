package com.excilys.cdb.dao;

import com.excilys.cdb.model.Company;

public interface CompanyDAO {

	void creer(Company company) throws DAOException;

	Company trouver(String nom) throws DAOException;

}
