package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;

@Component
public class CompanyController {

	/**
	 * Default Constructor.
	 */
	@Autowired
	CompanyService companyService;

	public CompanyController() {
		super();
		this.companyService = new CompanyService();

	}

	public List<Company> searchAllCompany() {
		return companyService.searchAllCompany();
	}

	public boolean deleteCompany(Long compToDeleteID) {
		return companyService.deleteCompany(compToDeleteID);
	}
}
