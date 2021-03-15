package com.excilys.cdb.controller;

import com.excilys.cdb.view.CliMenu;

public class CliMainMenu {

	private static final int OPTION_COMPANY = 1;
	private static final int OPTION_COMPUTER = 2;
	private static final int OPTION_EXIT = 3;

	CliComputerMenu cliComputerMenu;
	CliCompanyMenu cliCompanyMenu;
	CliMenu cliMenu;

	/**
	 * Default Constructor.
	 */
	public CliMainMenu() {
		super();
		this.cliComputerMenu = new CliComputerMenu();
		this.cliCompanyMenu = new CliCompanyMenu();
		this.cliMenu = new CliMenu();
	}

	public void mainMenu() {
		int choix = 0;
		while (choix != OPTION_EXIT) {
			choix = showMainMenu();
			switch (choix) {

			case OPTION_COMPANY:
				cliCompanyMenu.companyMenu();
				break;
			case OPTION_COMPUTER:
				cliMenu.computerMenu();
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
		CliMenu.showMainMenu();
		return CliMenu.askInputMainMenu();

	}

}
