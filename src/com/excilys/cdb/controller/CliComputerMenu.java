package com.excilys.cdb.controller;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;
import com.excilys.cdb.view.InputException;

public class CliComputerMenu {

	private static final int OPTION_SEARCH_ALL = 1;
	private static final int OPTION_SEARCH_ALL_PAGINATION = 2;
	private static final int OPTION_SEARCH_BY_ID = 3;
	private static final int OPTION_CREATE = 4;
	private static final int OPTION_UPDATE = 5;
	private static final int OPTION_DELETE = 6;
	private static final int OPTION_BACK_TO_MAIN = 7;
	private static final int OPTION_EXIT = 8;

	private static final int OPTION_BACK = 1;
	private static final int OPTION_NEXT = 2;
	private static final int OPTION_PAGE = 3;
	private static final int OPTION_BACK_TO_COMPUTER_MENU = 4;
	private static final int OPTION_EXIT_PAGINATION = 5;

	/**
	 * Default Constructor.
	 */
	public CliComputerMenu() {
		super();
	}

	public static void computerMenu() {
		int choix = 0;
		computerLoop: while (choix != OPTION_EXIT) {
			choix = showMainMenu();
			switch (choix) {

			case OPTION_SEARCH_ALL:
				searchAllComputer();
				break;
			case OPTION_SEARCH_ALL_PAGINATION:
				Page.searchAllComputerPagination();

				break;

			case OPTION_SEARCH_BY_ID:
				searchByIdComputer();
				break;
			case OPTION_CREATE:
				createComputer();
				break;
			case OPTION_UPDATE:
				updateComputer();
				break;
			case OPTION_DELETE:
				deleteComputer();
				break;
			case OPTION_BACK_TO_MAIN:
				break computerLoop;
			case OPTION_EXIT:
				System.exit(0);
				break;
			default:
				System.out.println("Sorry, please enter valid Option");

			}
		}

	}

	private static void searchAllComputer() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		for (Computer comp : computerDAOImpl.searchAll()) {
			System.out.println(comp.toString());
		}
	}

	private static void searchByIdComputer() {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Long idToSearch = CliMenu.searchOneComputer();
			Computer compSearched = computerDAOImpl.search(idToSearch);
			if (compSearched == null) {
				throw new InputException("L'ordinateur n'existe pas");
			} else {
				System.out.println(compSearched.toString());
			}
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.computerMenu();
		}
	}

	private static void createComputer() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		Computer compToCreate = CliMenu.createOneComputer();
		if (CliMenu.validateCreation(compToCreate)) {
			computerDAOImpl.create(compToCreate);
			System.out.println(compToCreate.toString() + " created");
		}
	}

	private static void updateComputer() {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);

			Computer compToUpdate = CliMenu.updateOneComputer();
			compToUpdate = computerDAOImpl.search(compToUpdate.getId());
			if (compToUpdate == null) {
				throw new InputException("L'ordinateur n'existe pas");
			}
			computerDAOImpl.update(compToUpdate);
			System.out.println(compToUpdate.toString() + " updated");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.computerMenu();
		}
	}

	private static void deleteComputer() {
		try {

			DAOFactory daoFactory = DAOFactory.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Computer compToDelete = CliMenu.deleteOneComputer();
			compToDelete = computerDAOImpl.search(compToDelete.getId());
			if (compToDelete == null) {
				throw new InputException("L'ordinateur n'existe pas");
			}
			computerDAOImpl.delete(compToDelete);
			System.out.println(compToDelete.toString() + " deleted");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.computerMenu();
		}
	}

	public static int showMainMenu() {

		return CliMenu.computerMenu();

	}

//	public static int showComputerPaginationMenu() {
//		return CliMenu.paginationComputerMenu();
//	}
//
//	private static void searchAllComputerPagination() {
//
//		DAOFactory daoFactory = DAOFactory.getInstance();
//		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
//		int page = 0;
//		int pageInitial = 0;
//		do {
//			if (computerDAOImpl.searchAllPagination(page).isEmpty()) {
//				page = pageInitial;
//			}
//			for (Computer comp : computerDAOImpl.searchAllPagination(page)) {
//				System.out.println(comp.toString());
//			}
//			pageInitial = page;
//			page = paginationComputer(page);
//
//		} while (page != -1);
//	}
//
//	public static int paginationComputer(int page) {
//		int choix = 0;
//		try {
//			while (choix != OPTION_EXIT_PAGINATION) {
//				choix = showComputerPaginationMenu();
//				switch (choix) {
//
//				case OPTION_BACK:
//					if (page != 0) {
//						return page -= 1;
//					} else {
//						throw new InputException("Impossible d'aller en arrière car page 0");
//					}
//				case OPTION_NEXT:
//					return page += 1;
//				case OPTION_PAGE:
//					return CliMenu.choixPage();
//				case OPTION_BACK_TO_COMPUTER_MENU:
//					return -1;
//				case OPTION_EXIT_PAGINATION:
//					System.exit(0);
//					break;
//				default:
//					System.out.println("Sorry, please enter valid Option");
//					paginationComputer(page);
//				}
//			}
//		} catch (InputException e) {
//			System.out.println(e.getMessage());
//			paginationComputer(page);
//		}
//		return choix;
//	}

}
