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

	private Page page;

	/**
	 * Default Constructor.
	 */
	public CliComputerMenu() {
		super();
		this.page = new Page();
	}

	public void computerMenu() {
		int choix = 0;
		computerLoop: while (choix != OPTION_EXIT) {
			choix = showMainMenu();
			switch (choix) {

			case OPTION_SEARCH_ALL:
				searchAllComputer();
				break;
			case OPTION_SEARCH_ALL_PAGINATION:
				page.searchAllComputerPagination();
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
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Computer compToCreate = CliMenu.createOneComputer();
			if (CliMenu.validateCreation(compToCreate)) {
				computerDAOImpl.create(compToCreate);
				System.out.println(compToCreate.toString() + " created");
			}
		} catch (com.excilys.cdb.dao.DAOException e) {
			System.out.println("Date cannot be bellow 1970-01-01 Or company doesn't exist");
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

}
