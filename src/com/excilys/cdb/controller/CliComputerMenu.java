package com.excilys.cdb.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
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

		DBConnexion daoFactory = DBConnexion.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		for (Computer comp : computerDAOImpl.searchAll()) {
			System.out.println(comp.toString());
		}
	}

	private static void searchByIdComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Long idToSearch = CliMenu.searchOneComputer();
			Optional<Computer> compSearched = computerDAOImpl.search(idToSearch);
			System.out.println(compSearched.orElseThrow().toString());
		} catch (NoSuchElementException e) {
			System.out.println("L'ordinateur n'existe pas");
			CliMenu.computerMenu();
		}
	}

	private static void createComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Optional<Computer> compToCreate = CliMenu.createOneComputer();
			if (CliMenu.validateCreation(compToCreate)) {
				computerDAOImpl.create(compToCreate.orElseThrow());
				System.out.println(compToCreate.toString() + " created");
			}
//		} catch (com.excilys.cdb.dao.DAOException e) {
//			System.out.println("Date cannot be bellow 1970-01-01 Or company doesn't exist");
//		} 
		} catch (NoSuchElementException e) {
			System.out.println("The computer cannot be created");
		}
	}

	private static void updateComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);

			Computer compToUpdate = CliMenu.updateOneComputer();

			computerDAOImpl.update(compToUpdate);
			System.out.println(compToUpdate.toString() + " updated");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.computerMenu();
		}
	}

	private static void deleteComputer() {
		try {

			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			Long compToDeleteID = CliMenu.deleteOneComputer();

			computerDAOImpl.delete(compToDeleteID);
			System.out.println("Computer " + compToDeleteID + ": deleted");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.computerMenu();
		}
	}

	public static int showMainMenu() {

		return CliMenu.computerMenu();

	}

}
