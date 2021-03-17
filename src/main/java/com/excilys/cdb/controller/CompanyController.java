package com.excilys.cdb.controller;

import java.util.List;

import com.excilys.cdb.model.Company;
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

	public List<Company> searchAllCompany() {
		return companyService.searchAllCompany();
	}

}
