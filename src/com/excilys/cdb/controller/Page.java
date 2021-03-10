package com.excilys.cdb.controller;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;
import com.excilys.cdb.view.InputException;

public class Page {
	private static final int OPTION_BACK = 1;
	private static final int OPTION_NEXT = 2;
	private static final int OPTION_PAGE = 3;
	private static final int OPTION_BACK_TO_MENU = 4;
	private static final int OPTION_EXIT_PAGINATION = 5;

	public Page() {
		super();
	}

	public static int showComputerPaginationMenu() {
		return CliMenu.paginationrMenu();
	}

	public static void searchAllComputerPagination() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		int page = 0;
		int pageInitial = 0;
		do {
			if (computerDAOImpl.searchAllPagination(page).isEmpty()) {
				page = pageInitial;
			}
			System.out.println("Page " + page);
			for (Computer comp : computerDAOImpl.searchAllPagination(page)) {
				System.out.println(comp.toString());
			}
			pageInitial = page;
			page = paginationComputer(page);

		} while (page != -1);
	}

	public static int paginationComputer(int page) {
		int choix = 0;
		try {
			while (choix != OPTION_EXIT_PAGINATION) {
				choix = showComputerPaginationMenu();
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
					return -1;
				case OPTION_EXIT_PAGINATION:
					System.exit(0);
					break;
				default:
					System.out.println("Sorry, please enter valid Option");
					paginationComputer(page);
				}
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			paginationComputer(page);
		}
		return choix;
	}

	// TO DO PAGINATION COMPANY (VIEUX COPIER COLLER)
	// FAIRE ATTENTION AU METHODE
	// voir a enlever car repetition de code
	public static void searchAllCompanyPagination() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		int page = 0;
		int pageInitial = 0;
		do {
			if (computerDAOImpl.searchAllPagination(page).isEmpty()) {
				page = pageInitial;
			}
			System.out.println("Page " + page);
			for (Computer comp : computerDAOImpl.searchAllPagination(page)) {
				System.out.println(comp.toString());
			}
			pageInitial = page;
			page = paginationCompany(page);

		} while (page != -1);
	}

	public static int paginationCompany(int page) {
		int choix = 0;
		try {
			while (choix != OPTION_EXIT_PAGINATION) {
				choix = showComputerPaginationMenu();
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
					return -1;
				case OPTION_EXIT_PAGINATION:
					System.exit(0);
					break;
				default:
					System.out.println("Sorry, please enter valid Option");
					paginationComputer(page);
				}
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			paginationComputer(page);
		}
		return choix;
	}

}
