package com.excilys.cdb.controller;

import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.view.CliMenu;

public class CliCompanyMenuController {

	/**
	 * Default Constructor.
	 */

	CompanyService companyService;

	public CliCompanyMenuController() {
		super();
		this.companyService = new CompanyService();

	}

	public void searchAllCompany() {

		companyService.searchAllCompany();

	}

	public int showCompanyMenuAndAskInput() {
		CliMenu.showCompanyMenu();
		return CliMenu.askCompanyMenuInput();

	}
}
