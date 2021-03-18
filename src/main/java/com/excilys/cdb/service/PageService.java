package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliPage;

public class PageService {
	CliPage cliPage;

	public PageService() {

	}

	/**
	 * @param pageInt the page searched
	 * @return
	 */
	public List<Computer> searchAllComputerPagination(int pageInt, int objectPerPage) {
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
		return computerDAOImpl.searchAllPagination(pageInt, objectPerPage);
	}

	public List<Company> searchAllCompanyPage(int pageInt) {
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		return companyDAOImpl.searchAllPagination(pageInt);
	}

}
