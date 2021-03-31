package com.excilys.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.PageService;

@Component
public class PageController {
	@Autowired
	private PageService pageService;

	public List<Computer> searchAllComputerPagination(Page<Computer> page) {

		return pageService.searchAllComputerPagination(page);
	}

	public List<Company> searchAllCompanyPagination(int pageInt) {

		return pageService.searchAllCompanyPage(pageInt);
	}

}
