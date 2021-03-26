package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.exception.DAOConfigurationException;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.view.CliPage;

public class PageService {
	CliPage cliPage;

	public PageService() {

	}

	/**
	 * @param pageInt the page searched
	 * @return
	 */
	public List<Computer> searchAllComputerPagination(Page<Computer> page) {
		try {
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			return computerDAOImpl.searchAllPagination(page);
		} catch (

		DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Computer>();
	}

	public List<Computer> searchNamePagination(Page<Computer> page, String name) {
		try {
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			return computerDAOImpl.searchNamePagination(page, name);
		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Computer>();
	}

	public List<Company> searchAllCompanyPage(int pageInt) {
		try {
			CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
			return companyDAOImpl.searchAllPagination(pageInt);
		} catch (

		DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Company>();
	}

}
