package com.excilys.cdb.controller;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.PageService;

public class PageController {

	private Page<Computer> pageComputer;
	private Page<Company> pageCompany;
	private PageService pageService;

	public PageController() {
		this.pageComputer = new Page<Computer>();
		this.pageCompany = new Page<Company>();
		pageService = new PageService();
	}

	public void searchAllComputerPagination() {

		pageService.searchAllComputerPagination(pageComputer);
	}

	public void searchAllCompanyPageUseDAO() {

		pageService.searchAllCompanyPageUseDAO(pageCompany);
	}

}
