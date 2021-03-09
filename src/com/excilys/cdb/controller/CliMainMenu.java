package com.excilys.cdb.controller;

import static com.excilys.cdb.controller.CliCompanyMenu.companyMenu;
import static com.excilys.cdb.controller.CliComputerMenu.computerMenu;

import com.excilys.cdb.view.CliMenu;

public class CliMainMenu {

	private static final int OPTION_COMPANY = 1;
	private static final int OPTION_COMPUTER = 2;
	private static final int OPTION_EXIT = 3;

	/**
	 * Default Constructor
	 */
	public CliMainMenu() {
		super();

	}

	public static void mainMenu() {
		int choix = 0;
		while (choix != OPTION_EXIT) {
			choix = showMainMenu();
			switch (choix) {

			case OPTION_COMPANY:
				companyMenu();
				break;
			case OPTION_COMPUTER:
				computerMenu();
				break;
			case OPTION_EXIT:
				System.exit(0);
				break;
			default:
				System.out.println("Sorry, please enter valid Option");

			}
		}

	}

	public static int showMainMenu() {

		return CliMenu.mainMenu();
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
