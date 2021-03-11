package com.excilys.cdb.controller;

import java.util.List;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;
import com.excilys.cdb.view.InputException;

public class Page {
	private static final int OPTION_BACK = 1;
	private static final int OPTION_NEXT = 2;
	private static final int OPTION_PAGE = 3;
	private static final int OPTION_BACK_TO_MENU = 4;
	private static final int OPTION_EXIT_PAGINATION = 5;

	private List<Computer> contentPageComputer;
	private List<Company> contentPageCompany;
	private int page;
	private int pageInitial;

	public Page() {
		super();
		this.page = 0;
		this.pageInitial = 0;
		this.contentPageComputer = null;
		this.contentPageCompany = null;
	}

	public static int showPageMenuAndAskInput() {
		return CliMenu.paginationrMenu();
	}

	public void searchAllComputerPagination() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		page = 0;
		pageInitial = 0;
		do {
			contentPageComputer = computerDAOImpl.searchAllPagination(page);
			if (contentPageComputer.isEmpty()) {
				System.out.println("Page " + page + " doesn't exist.");
				page = pageInitial;
			}
			contentPageComputer = computerDAOImpl.searchAllPagination(page);
			System.out.println("Page " + page);
			for (Computer comp : contentPageComputer) {
				System.out.println(comp.toString());
			}
			pageInitial = page;
			page = pageComputerVerifyChoice(page);

		} while (page != -1);
	}

	public int pageComputerVerifyChoice(int page) {
		int choix = 0;
		try {
			while (choix != OPTION_EXIT_PAGINATION) {
				choix = showPageMenuAndAskInput();
				switch (choix) {

				case OPTION_BACK:
					if (page != 0) {
						return page -= 1;
					} else {
						throw new InputException("Impossible to go bellow page 0");
					}
				case OPTION_NEXT:
					return page += 1;
				case OPTION_PAGE:
					return CliMenu.choixPage();
				case OPTION_BACK_TO_MENU:
					contentPageComputer = null;
					return -1;
				case OPTION_EXIT_PAGINATION:
					System.exit(0);
					break;
				default:
					System.out.println("Sorry, please enter valid Option");
					pageComputerVerifyChoice(page);
				}
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			choix = pageComputerVerifyChoice(page);
		}
		return choix;
	}

	public void searchAllCompanyPageUseDAO() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
		page = 0;
		pageInitial = 0;
		do {
			contentPageCompany = companyDAOImpl.searchAllPagination(page);
			if (contentPageCompany.isEmpty()) {
				System.out.println("Page " + page + " doesn't exist.");
				page = pageInitial;
			}
			contentPageCompany = companyDAOImpl.searchAllPagination(page);
			System.out.println("Page " + page);
			showCompanyContent();
			pageInitial = page;
			page = pageCompanyVerifChoice(page);

		} while (page != -1);
	}

	private void showCompanyContent() {
		for (Company comp : contentPageCompany) {
			System.out.println(comp.toString());
		}
	}

	public int pageCompanyVerifChoice(int page) {
		int choix = 0;
		try {
			while (choix != OPTION_EXIT_PAGINATION) {
				choix = showPageMenuAndAskInput();
				switch (choix) {

				case OPTION_BACK:
					if (page != 0) {
						return page -= 1;
					} else {
						throw new InputException("Impossible d'aller en arrière car page 0");
					}
				case OPTION_NEXT:
					return page += 1;
				case OPTION_PAGE:
					return CliMenu.choixPage();
				case OPTION_BACK_TO_MENU:
					contentPageCompany = null;
					return -1;
				case OPTION_EXIT_PAGINATION:
					System.exit(0);
					break;
				default:
					throw new InputException("Veuillez entrer une valeur valide");
				// System.out.println("Sorry, please enter valid Option");
				// paginationCompany(page);
				}
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			showCompanyContent();
			choix = pageCompanyVerifChoice(page);
		}
		return choix;
	}

}
