package com.excilys.cdb.controller;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.PageService;

public class PageController {

	private PageService pageService;

	public PageController() {

		pageService = new PageService();
	}

	public List<Computer> searchAllComputerPagination(int pageInt, int objectPerPage) {

		return pageService.searchAllComputerPagination(pageInt, objectPerPage);
	}

	public List<Company> searchAllCompanyPagination(int pageInt) {

		return pageService.searchAllCompanyPage(pageInt);
	}

}
