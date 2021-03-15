package com.excilys.cdb.service;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.view.CliMenu;

public class CompanyService {
	public void searchAllCompany() {

		DBConnexion daoFactory = DBConnexion.getInstance();
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
		for (Company comp : companyDAOImpl.searchAll()) {
			System.out.println(comp.toString());
		}
	}

	public int showCompanyMenuAndAskInput() {
		CliMenu.showCompanyMenu();
		return CliMenu.askCompanyMenuInput();

	}
}
