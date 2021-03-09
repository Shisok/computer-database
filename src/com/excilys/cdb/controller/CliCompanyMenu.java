package com.excilys.cdb.controller;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.view.CliMenu;

public class CliCompanyMenu {

	private static final int OPTION_SEARCH_ALL = 1;
	private static final int OPTION_BACK = 2;
	private static final int OPTION_EXIT = 3;

	/**
	 * Default Constructor
	 */
	public CliCompanyMenu() {
		super();
	}

	public static void companyMenu() {
		int choix = 0;
		companyLoop: while (choix != OPTION_EXIT) {
			choix = showCompanyMenu();
			switch (choix) {

			case OPTION_SEARCH_ALL:
				searchAllCompany();
				break;
			case OPTION_BACK:
				break companyLoop;
			case OPTION_EXIT:
				System.exit(0);
			default:
				System.out.println("Sorry, please enter valid Option");

			}

		}
	}

	private static void searchAllCompany() {

		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
		for (Company comp : companyDAOImpl.searchAll()) {
			System.out.println(comp.toString());
		}
	}

	private static int showCompanyMenu() {
		return CliMenu.companyMenu();
//		int choix = 0;
//		Scanner keyboard = null;
//		try {
//			keyboard = new Scanner(System.in);
//			choix = keyboard.nextInt();
//		} catch (InputMismatchException e) {
//			System.out.println("Vous n'avez pas écrit un chiffre");
//			showCompanyMenu();
//		}
//
//		return choix;
	}
}
