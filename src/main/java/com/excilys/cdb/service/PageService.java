package com.excilys.cdb.service;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOConfigurationException;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.view.CliPage;

public class PageService {
	CliPage cliPage;

	public PageService() {
		this.cliPage = new CliPage();
	}

	public void searchAllComputerPagination(Page<Computer> page) {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			page.setPageInt(0);
			page.setPageInitial(0);

			do {
				page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
				if (page.getContentPage().isEmpty()) {
					System.out.println("Page " + page.getPageInt() + " doesn't exist.");
					page.setPageInt(page.getPageInitial());
				}
				page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
				System.out.println("Page " + page.getPageInt());
				page.getContentPage().stream().forEach(c -> System.out.println(c.toString()));

				page.setPageInitial(page.getPageInt());
				page.setPageInt(cliPage.pageComputerVerifyChoice(page.getPageInt()));

			} while (page.getPageInt() != -1);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	public void searchAllCompanyPageUseDAO(Page<Company> page) {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
			page.setPageInt(0);
			page.setPageInitial(0);
			do {
				page.setContentPage(companyDAOImpl.searchAllPagination(page.getPageInt()));
				if (page.getContentPage().isEmpty()) {
					System.out.println("Page " + page.getPageInt() + " doesn't exist.");
					page.setPageInt(page.getPageInitial());
				}
				page.setContentPage(companyDAOImpl.searchAllPagination(page.getPageInt()));
				System.out.println("Page " + page.getPageInt());
				showCompanyContent(page);
				page.setPageInitial(page.getPageInt());
				page.setPageInt(cliPage.pageCompanyVerifChoice(page.getPageInt()));

			} while (page.getPageInt() != -1);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	private void showCompanyContent(Page<Company> page) {
		page.getContentPage().stream().forEach(c -> System.out.println(c.toString()));

	}
}
