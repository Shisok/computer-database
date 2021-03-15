package com.excilys.cdb.controller;

import com.excilys.cdb.service.CompanyService;

public class CompanyController {

	/**
	 * Default Constructor.
	 */

	CompanyService companyService;

	public CompanyController() {
		super();
		this.companyService = new CompanyService();

	}

	public void searchAllCompany() {
		companyService.searchAllCompany();
	}

}
