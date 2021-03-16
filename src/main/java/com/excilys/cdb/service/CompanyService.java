package com.excilys.cdb.service;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.view.CliMenu;

public class CompanyService {
	public void searchAllCompany() {

		DBConnexion daoFactory = DBConnexion.getInstance();
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
		companyDAOImpl.searchAll().stream().forEach(c -> System.out.println(c.toString()));

	}

	public int showCompanyMenuAndAskInput() {
		CliMenu.showCompanyMenu();
		return CliMenu.askCompanyMenuInput();

	}
}
