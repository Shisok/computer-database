package com.excilys.cdb.controller;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;

public class CliComputerMenu {

	private static final int OPTION_SEARCH_ALL = 1;
	private static final int OPTION_SEARCH_BY_ID = 2;
	private static final int OPTION_CREATE = 3;
	private static final int OPTION_UPDATE = 4;
	private static final int OPTION_DELETE = 5;
	private static final int OPTION_BACK_TO_MAIN = 6;
	private static final int OPTION_EXIT = 7;

	/**
	 * Default Constructor
	 */
	public CliComputerMenu() {
		super();
	}

	public static void computerMenu() {
		int choix = 0;
		while (choix != OPTION_EXIT) {
			choix = showMainMenu();
			switch (choix) {

			case OPTION_SEARCH_ALL:
				searchAllComputer();
				break;
			case OPTION_SEARCH_BY_ID:
				searchByIdComputer();
				break;
			case OPTION_CREATE:
				createComputer();
				break;
			case OPTION_UPDATE:
				break;
			case OPTION_DELETE:
				break;
			case OPTION_BACK_TO_MAIN:
				break;
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

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		Long idToSearch = CliMenu.searchOneComputer();
		Computer compSearched = computerDAOImpl.search(idToSearch);
		if (compSearched == null) {
			System.out.println("L'ordinateur n'existe pas");
		} else {
			compSearched.toString();
		}
	}

	private static void createComputer() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		Computer compToCreate = CliMenu.createOneComputer();
		System.out.println(compToCreate.toString());
		computerDAOImpl.create(compToCreate);
	}

	public static int showMainMenu() {

		return CliMenu.computerMenu();
//		int choix = 0;
//		Scanner keyboard = null;
//		try {
//			keyboard = new Scanner(System.in);
//
//			choix = keyboard.nextInt();
//		} catch (InputMismatchException e) {
//			System.out.println("Vous n'avez pas écrit un chiffre");
//			showMainMenu();
//		}
//
//		return choix;

	}
}
