package com.excilys.cdb.service;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DAOConfigurationException;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.view.CliMenu;

public class CompanyService {
	public void searchAllCompany() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
			companyDAOImpl.searchAll().stream().forEach(c -> System.out.println(c.toString()));
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}

	}

	public int showCompanyMenuAndAskInput() {
		CliMenu.showCompanyMenu();
		return CliMenu.askCompanyMenuInput();

	}
}
