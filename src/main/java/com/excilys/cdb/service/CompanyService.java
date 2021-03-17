package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DAOConfigurationException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.view.CliMenu;

public class CompanyService {
	public List<Company> searchAllCompany() {
		try {

			CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
			return companyDAOImpl.searchAll();

		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Company>();

	}

	public int showCompanyMenuAndAskInput() {
		CliMenu.showCompanyMenu();
		return CliMenu.askCompanyMenuInput();

	}
}
