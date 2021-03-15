package com.excilys.cdb.service;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
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
			for (Computer comp : page.getContentPage()) {
				System.out.println(comp.toString());
			}
			page.setPageInitial(page.getPageInt());
			page.setPageInt(cliPage.pageComputerVerifyChoice(page.getPageInt()));

		} while (page.getPageInt() != -1);
	}

	public void searchAllCompanyPageUseDAO(Page<Company> page) {

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
	}

	private void showCompanyContent(Page<Company> page) {
		for (Company comp : page.getContentPage()) {
			System.out.println(comp.toString());
		}
	}
}
