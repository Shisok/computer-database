package com.excilys.cdb.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.cdb.model.Computer;

public class CliMenu {

	private static final String SCANNER_DELIMITER = "\\s*,\\s*";
	private static final Scanner USER_INPUT = new Scanner(System.in);

	public CliMenu() {
		super();
	}

	public static int mainMenu() {
		lineSeparator();
		System.out.println("Main Menu:");
		lineSeparator();
		System.out.println("1.Menu Company");
		System.out.println("2.Menu Computer");
		System.out.println("3.Quit");
		lineSeparator();
		enterChoice();
		int choix = 0;

		try {

			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			mainMenu();
		}

		return choix;
	}

	private static void lineSeparator() {
		System.out.println("--------------");
	}

	private static void enterChoice() {
		System.out.print("Enter your choice:");
	}

	public static int companyMenu() {
		lineSeparator();
		System.out.println("Company Menu:");
		lineSeparator();
		System.out.println("1.Search All Companies");
		System.out.println("2.Back to Main Menu");
		System.out.println("3.Quit");
		lineSeparator();
		enterChoice();

		int choix = 0;
		// Scanner keyboard = null;
		try {
			// keyboard = new Scanner(System.in);
//			choix = USER_INPUT.nextInt();
//		} catch (InputMismatchException e) {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			companyMenu();
		}

		return choix;
	}

	public static int computerMenu() {
		lineSeparator();
		System.out.println("Company Menu:");
		lineSeparator();
		System.out.println("1.Search All Computer");
		System.out.println("2.Search All Computer With Pagination");
		System.out.println("3.Search One Computer By Id");
		System.out.println("4.Create One Computer");
		System.out.println("5.Update One Computer");
		System.out.println("6.Delete One Computer");
		System.out.println("7.Back to Main Menu");
		System.out.println("8.Quit");
		lineSeparator();
		enterChoice();

		int choix = 0;
		try {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			computerMenu();
		}

		return choix;
	}

	public static Long searchOneComputer() {
		lineSeparator();
		System.out.println("Computer To Search By Id:");

		Long choix = 0L;
		try {
			choix = Long.parseLong(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			searchOneComputer();
		}

		return choix;
	}

	public static Computer createOneComputer() {
		lineSeparator();
		System.out.println(
				"Computer To Create [name, introduced, discontinued, company_id] (Write null if null And Date in format :YYYY-MM-DD):");
		String input = null;
		String name = "";
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// Scanner keyboard = null;
		Computer computer = null;
		try {
			// keyboard = new Scanner(System.in);
			input = USER_INPUT.nextLine();
			String[] inputArray = input.split(SCANNER_DELIMITER);
			if (inputArray.length != 4) {
				throw new InputException("You didn't enter the right number of argument.");
			}
			if (!inputArray[0].equals("")) {
				name = inputArray[0];
			}
			if (!inputArray[1].equals("null")) {
				introduced = LocalDate.parse(inputArray[1]);
			}

			if (!inputArray[2].equals("null")) {
				discontinued = LocalDate.parse(inputArray[2]);
				if (introduced.isAfter(discontinued) || introduced.equals(discontinued)) {
					throw new InputException("Discontinued is before introduced");
				}
			}

			if (!inputArray[3].equals("null")) {
				companyId = Long.parseLong(inputArray[3]);
			}

			computer = new Computer.ComputerBuilder(null).name(name).introduced(introduced).discontinued(discontinued)
					.companyId(companyId).build();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value.");
			computer = createOneComputer();
		} catch (DateTimeParseException e) {
			System.out.println("You didn't the date in the right format.");
			computer = createOneComputer();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You didn't enter the right number of argument.");
			computer = createOneComputer();
		} catch (InputException e) {
			System.out.println(e.getMessage());
			computer = createOneComputer();
		}

		return computer;
	}

	public static Computer updateOneComputer() {
		lineSeparator();
		System.out.println(
				"Computer To Update [Id, name, introduced, discontinued, company_id] (Write null if null And Date in format :YYYY-MM-DD) :");
		String input = null;
		String name = "";
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		// Scanner keyboard = null;
		Computer computer = null;
		Long id = null;
		try {

			// keyboard = new Scanner(System.in);
			input = USER_INPUT.nextLine();
			String[] inputArray = input.split(SCANNER_DELIMITER);
			if (inputArray.length != 5) {
				throw new InputException("You didn't enter the right number of argument.");
			}
			if (!inputArray[0].equals("null")) {
				id = Long.parseLong(inputArray[0]);
			}
			if (!inputArray[1].equals("")) {
				name = inputArray[1];
			}
			if (!inputArray[2].equals("null")) {
				introduced = LocalDate.parse(inputArray[2]);
			}

			if (!inputArray[3].equals("null")) {
				discontinued = LocalDate.parse(inputArray[3]);
				if (introduced.isAfter(discontinued) || introduced.equals(discontinued)) {
					throw new InputException("Discontinued is before introduced");
				}
			}

			if (!inputArray[4].equals("null")) {
				companyId = Long.parseLong(inputArray[4]);
			}

			computer = new Computer.ComputerBuilder(id).name(name).introduced(introduced).discontinued(discontinued)
					.companyId(companyId).build();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value.");
			computer = updateOneComputer();
		} catch (DateTimeParseException e) {
			System.out.println("You didn't the date in the right format.");
			computer = updateOneComputer();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You didn't enter the right number of argument.");
			computer = updateOneComputer();
		} catch (InputException e) {
			System.out.println(e.getMessage());
			computer = updateOneComputer();
		}

		return computer;
	}

	public static Computer deleteOneComputer() {
		lineSeparator();
		System.out.print("Computer to delete id: ");
		Computer computer = null;
		Long id = null;
		try {

			id = Long.parseLong(USER_INPUT.nextLine());
			computer = new Computer.ComputerBuilder(id).build();
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value.");
			computer = deleteOneComputer();
		}

		return computer;
	}

	public static boolean validateCreation(Computer computer) {
		boolean validate = false;
		lineSeparator();
		System.out.println("Do you validate the creation of " + computer.toString());
		lineSeparator();
		System.out.println("1.Yes");
		System.out.println("2.No");
		lineSeparator();
		enterChoice();

		int choix = 0;
		try {

			choix = Integer.parseInt(USER_INPUT.nextLine());
			if (choix == 1) {
				validate = true;
			} else if (choix == 2) {
				validate = false;
			} else {
				System.out.println("Revalider votre choix");
				validate = validateCreation(computer);
			}
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			companyMenu();
		}

		return validate;

	}

	public static int paginationrMenu() {
		lineSeparator();
		System.out.println("Computer Pagination");
		lineSeparator();
		System.out.println("1.Back");
		System.out.println("2.Next");
		System.out.println("3.To a page number");
		System.out.println("4.Back to menu");
		System.out.println("5.Exit");
		lineSeparator();
		enterChoice();

		int choix = 0;
		try {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			paginationrMenu();
		}

		return choix;

	}

	public static int choixPage() {
		lineSeparator();
		System.out.print("Page to go: ");

		Integer choix = null;
		try {

			choix = Integer.parseInt(USER_INPUT.nextLine());

		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value.");
			choix = choixPage();
		}

		return choix;
	}

}
