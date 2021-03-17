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
		// this.cliPage = new CliPage();
	}

//	public void searchAllComputerPagination(Page<Computer> page) {
//		try {
//			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
//			page.setPageInt(0);
//			page.setPageInitial(0);
//
//			do {
//				page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
//				if (page.getContentPage().isEmpty()) {
//					System.out.println("Page " + page.getPageInt() + " doesn't exist.");
//					page.setPageInt(page.getPageInitial());
//				}
//				page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
//				System.out.println("Page " + page.getPageInt());
//				page.getContentPage().stream().forEach(c -> System.out.println(c.toString()));
//
//				page.setPageInitial(page.getPageInt());
//				page.setPageInt(cliPage.pageComputerVerifyChoice(page.getPageInt()));
//
//			} while (page.getPageInt() != -1);
//		} catch (DAOConfigurationException e) {
//			LoggerCdb.logError(getClass(), e);
//		}
//	}
	public List<Computer> searchAllComputerPagination(int pageInt) {
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
		return computerDAOImpl.searchAllPagination(pageInt);
	}

//	public void searchAllCompanyPage(Page<Company> page) {
//		try {
//
//			CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
//			page.setPageInt(0);
//			page.setPageInitial(0);
//			do {
//				page.setContentPage(companyDAOImpl.searchAllPagination(page.getPageInt()));
//				if (page.getContentPage().isEmpty()) {
//					System.out.println("Page " + page.getPageInt() + " doesn't exist.");
//					page.setPageInt(page.getPageInitial());
//				}
//				page.setContentPage(companyDAOImpl.searchAllPagination(page.getPageInt()));
//				System.out.println("Page " + page.getPageInt());
//				showCompanyContent(page);
//				page.setPageInitial(page.getPageInt());
//				page.setPageInt(cliPage.pageCompanyVerifChoice(page.getPageInt()));
//
//			} while (page.getPageInt() != -1);
//		} catch (DAOConfigurationException e) {
//			LoggerCdb.logError(getClass(), e);
//		}
//	}
//
//	private void showCompanyContent(Page<Company> page) {
//		page.getContentPage().stream().forEach(c -> System.out.println(c.toString()));
//
//	}

	public List<Company> searchAllCompanyPage(int pageInt) {
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		return companyDAOImpl.searchAllPagination(pageInt);
	}

}
