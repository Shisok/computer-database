package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

@Component
public class CompanyController {

	/**
	 * Default Constructor.
	 */

	CompanyService companyService;

	public CompanyController(CompanyService companyService) {

		this.companyService = companyService;
	}

	public List<Company> searchAllCompany() {
		return companyService.searchAllCompany();
	}

	public boolean deleteCompany(Long compToDeleteID) {
		return companyService.deleteCompany(compToDeleteID);
	}
}
