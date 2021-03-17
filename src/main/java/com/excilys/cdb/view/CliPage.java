package com.excilys.cdb.view;

import java.util.ArrayList;

import com.excilys.cdb.controller.PageController;
import com.excilys.cdb.dao.DAOConfigurationException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public class CliPage {
	private static final int OPTION_BACK = 1;
	private static final int OPTION_NEXT = 2;
	private static final int OPTION_PAGE = 3;
	private static final int OPTION_BACK_TO_MENU = 4;
	private static final int OPTION_EXIT_PAGINATION = 5;
	private Page<Computer> pageComputer;
	private Page<Company> pageCompany;
	private PageController pageController;

	public CliPage() {
		this.pageComputer = new Page<Computer>();
		this.pageCompany = new Page<Company>();
		this.pageController = new PageController();
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
					CliMenu.showChoixPage();
					return CliMenu.choixPageAskInput();
				case OPTION_BACK_TO_MENU:
					this.pageComputer.setContentPage(new ArrayList<Computer>());
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
			LoggerCdb.logInfo(this.getClass(), e);
			System.out.println(e.getMessage());
			choix = pageComputerVerifyChoice(page);
		}
		return choix;
	}

	public static int showPageMenuAndAskInput() {
		CliMenu.showPaginationrMenu();
		return CliMenu.paginationMenuAskInput();
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
					CliMenu.showChoixPage();
					return CliMenu.choixPageAskInput();
				case OPTION_BACK_TO_MENU:
					this.pageCompany.setContentPage(new ArrayList<Company>());
					return -1;
				case OPTION_EXIT_PAGINATION:
					System.exit(0);
					break;
				default:
					throw new InputException("Veuillez entrer une valeur valide");

				}
			}
		} catch (InputException e) {
			LoggerCdb.logInfo(this.getClass(), e);
			System.out.println(e.getMessage());
			showCompanyContent(this.pageCompany);
			choix = pageCompanyVerifChoice(page);
		}
		return choix;
	}

	private void showCompanyContent(Page<Company> page) {
		for (Company comp : page.getContentPage()) {
			System.out.println(comp.toString());
		}
	}

	public void searchAllComputerPagination() {
		try {
			// ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			pageComputer.setPageInt(0);
			pageComputer.setPageInitial(0);

			do {
				// page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
				pageComputer.setContentPage(pageController.searchAllComputerPagination(pageComputer.getPageInt()));
				if (pageComputer.getContentPage().isEmpty()) {
					System.out.println("Page " + pageComputer.getPageInt() + " doesn't exist.");
					pageComputer.setPageInt(pageComputer.getPageInitial());
				}
				// page.setContentPage(computerDAOImpl.searchAllPagination(page.getPageInt()));
				pageComputer.setContentPage(pageController.searchAllComputerPagination(pageComputer.getPageInt()));
				System.out.println("Page " + pageComputer.getPageInt());
				pageComputer.getContentPage().stream().forEach(c -> System.out.println(c.toString()));

				pageComputer.setPageInitial(pageComputer.getPageInt());
				pageComputer.setPageInt(pageComputerVerifyChoice(pageComputer.getPageInt()));

			} while (pageComputer.getPageInt() != -1);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	public void searchAllCompanyPage() {
		try {

			pageCompany.setPageInt(0);
			pageCompany.setPageInitial(0);
			do {

				pageCompany.setContentPage(pageController.searchAllCompanyPagination(pageCompany.getPageInt()));
				if (pageCompany.getContentPage().isEmpty()) {
					System.out.println("Page " + pageCompany.getPageInt() + " doesn't exist.");
					pageCompany.setPageInt(pageCompany.getPageInitial());
				}

				pageCompany.setContentPage(pageController.searchAllCompanyPagination(pageCompany.getPageInt()));
				System.out.println("Page " + pageCompany.getPageInt());
				showCompanyContent(pageCompany);
				pageCompany.setPageInitial(pageCompany.getPageInt());
				pageCompany.setPageInt(pageCompanyVerifChoice(pageCompany.getPageInt()));

			} while (pageCompany.getPageInt() != -1);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

}
