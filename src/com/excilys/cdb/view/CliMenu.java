package com.excilys.cdb.view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.cdb.model.Computer;

public class CliMenu {

	private static final String SCANNER_DELIMITER = "\\s*,\\s*";

	public CliMenu() {
		super();
	}

	public static int mainMenu() {
		System.out.println("Main Menu:");
		System.out.println("--------------");
		System.out.println("1.Menu Company");
		System.out.println("2.Menu Computer");
		System.out.println("3.Quit");
		System.out.println("--------------");
		enterChoice();
		int choix = 0;
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);

			choix = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value");
			mainMenu();
		}

		return choix;
	}

	private static void enterChoice() {
		System.out.print("Enter your choice:");
	}

	public static int companyMenu() {
		System.out.println("Company Menu:");
		System.out.println("--------------");
		System.out.println("1.Search All Companies");
		System.out.println("2.Back to Main Menu");
		System.out.println("3.Quit");
		System.out.println("--------------");
		enterChoice();

		int choix = 0;
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);
			choix = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value");
			companyMenu();
		}

		return choix;
	}

	public static int computerMenu() {
		System.out.println("Company Menu:");
		System.out.println("--------------");
		System.out.println("1.Search All Computer");
		System.out.println("2.Search One Computer By Id");
		System.out.println("3.Create One Computer");
		System.out.println("4.Update One Computer");
		System.out.println("5.Delete One Computer");
		System.out.println("6.Back to Main Menu");
		System.out.println("7.Quit");
		System.out.println("--------------");
		enterChoice();

		int choix = 0;
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);
			choix = keyboard.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value");
			computerMenu();
		}

		return choix;
	}

	public static Long searchOneComputer() {
		System.out.println("Computer To Search:");

		Long choix = 0L;
		Scanner keyboard = null;
		try {
			keyboard = new Scanner(System.in);
			choix = keyboard.nextLong();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value");
			searchOneComputer();
		}

		return choix;
	}

	public static Computer createOneComputer() {
		System.out.println(
				"Computer To Create [name, introduced, discontinued, company_id] (Write null if null And Date in format :YYYY-MM-DD :");
		String input = null;
		String name = "";
		LocalDate introduced = null;
//		String introducedString = null;
		LocalDate discontinued = null;
//		String discontinuedString = null;
		Long company_id = null;
//		String company_idString = null;
		Scanner keyboard = null;
		Computer computer = null;
		try {
//			keyboard = new Scanner(System.in).useDelimiter(SCANNER_DELIMITER);
//			name = keyboard.next();
//			System.out.println("name" + name);
//
//			introducedString = keyboard.next();
//			if (introducedString.equals("null")) {
//				introduced = null;
//			} else {
//				introduced = LocalDate.parse(introducedString);
//			}
//			System.out.println("introduced");
//			discontinuedString = keyboard.next();
//			if (discontinuedString.equals("null")) {
//				discontinued = null;
//			} else {
//				discontinued = LocalDate.parse(discontinuedString);
//			}
//			System.out.println("discontinued");
//
//			company_idString = keyboard.next();
//
//			System.out.println("after next");
//			if (company_idString.equals("null")) {
//				company_id = null;
//			} else {
//				company_id = Long.parseLong(company_idString);
//			}
//			System.out.println("Long");
			keyboard = new Scanner(System.in);
			input = keyboard.nextLine();
			String[] inputArray = input.split(SCANNER_DELIMITER);
			name = inputArray[0];
			if (!inputArray[1].equals("null")) {
				introduced = LocalDate.parse(inputArray[1]);
			}

			if (!inputArray[2].equals("null")) {
				discontinued = LocalDate.parse(inputArray[2]);
			}

			if (!inputArray[3].equals("null")) {
				company_id = Long.parseLong(inputArray[3]);
			}

			computer = new Computer.ComputerBuilder(null).name(name).introduced(introduced).discontinued(discontinued)
					.company_id(company_id).build();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value");
			createOneComputer();
		}

		return computer;
	}

}
