package com.excilys.cdb.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.cdb.controller.CompanyController;
import com.excilys.cdb.controller.ComputerController;
import com.excilys.cdb.controller.Page;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class CliMenu {

	private static final int OPTION_SEARCH_ALL_COMPUTER = 1;
	private static final int OPTION_SEARCH_ALL_PAGINATION_COMPUTER = 2;
	private static final int OPTION_SEARCH_BY_ID_COMPUTER = 3;
	private static final int OPTION_CREATE_COMPUTER = 4;
	private static final int OPTION_UPDATE_COMPUTER = 5;
	private static final int OPTION_DELETE_COMPUTER = 6;
	private static final int OPTION_BACK_TO_MAIN_COMPUTER = 7;
	private static final int OPTION_EXIT_COMPUTER = 8;
	private static final String SCANNER_DELIMITER = "\\s*,\\s*";
	private static final Scanner USER_INPUT = new Scanner(System.in);
	private Page page;
	private ComputerController cliComputerMenuController;
	private static final int OPTION_SEARCH_ALL_COMPANY = 1;
	private static final int OPTION_SEARCH_ALL_PAGINATION_COMPANY = 2;
	private static final int OPTION_BACK_COMPANY = 3;
	private static final int OPTION_EXIT_COMPANY = 4;
	private CompanyController cliCompanyMenuController;
	private static final int OPTION_COMPANY_MAIN = 1;
	private static final int OPTION_COMPUTER_MAIN = 2;
	private static final int OPTION_EXIT_MAIN = 3;

	public CliMenu() {
		super();
		this.page = new Page();
		this.cliComputerMenuController = new ComputerController();
		this.cliCompanyMenuController = new CompanyController();
	}

	public static void showMainMenu() {
		lineSeparator();
		System.out.println("Main Menu:");
		lineSeparator();
		System.out.println("1.Menu Company");
		System.out.println("2.Menu Computer");
		System.out.println("3.Quit");
		lineSeparator();
		enterChoice();

	}

	public static int askInputMainMenu() {
		int choix = 0;
		try {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showMainMenu();
			choix = askInputMainMenu();
		}

		return choix;
	}

	private static void lineSeparator() {
		System.out.println("--------------");
	}

	private static void enterChoice() {
		System.out.print("Enter your choice:");
	}

	public static void showCompanyMenu() {
		lineSeparator();
		System.out.println("Company Menu:");
		lineSeparator();
		System.out.println("1.Search All Companies");
		System.out.println("2.Search All Companies With Pagination");
		System.out.println("3.Back to Main Menu");
		System.out.println("4.Quit");
		lineSeparator();
		enterChoice();

	}

	public static int askCompanyMenuInput() {
		int choix = 0;
		try {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showCompanyMenu();
			choix = askCompanyMenuInput();
		}

		return choix;
	}

	public static void showComputerMenu() {
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

	}

	public static int computerMenuAskInput() {
		int choix = 0;
		try {
			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showComputerMenu();
			choix = computerMenuAskInput();
		}

		return choix;
	}

	public static void showSearchOneComputer() {
		lineSeparator();
		System.out.println("Computer To Search By Id:");

	}

	public static Long searchOneComputerAskInput() {
		Long choix = 0L;
		try {
			choix = Long.parseLong(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showSearchOneComputer();
			choix = searchOneComputerAskInput();
		}

		return choix;
	}

	public static void showCreateOneComputer() {
		lineSeparator();
		System.out.println(
				"Computer To Create [name, introduced, discontinued, company_id] (Write null if no data And Date in format :YYYY-MM-DD):");

	}

	public static Optional<Computer> createOneComputerAskInput() {
		String input = null;
		String name = "";
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Long companyId = null;
		Optional<Computer> computer = Optional.empty();
		try {
			input = USER_INPUT.nextLine();
			String[] inputArray = input.split(SCANNER_DELIMITER);
			if (inputArray.length != 4) {
				throw new InputException("You didn't enter the right number of argument.");
			}
			if (!("").equals(inputArray[0])) {
				name = inputArray[0];
			}
			if (!"null".equals(inputArray[1])) {
				introduced = LocalDate.parse(inputArray[1]);
			}

			if (!"null".equals(inputArray[2])) {
				discontinued = LocalDate.parse(inputArray[2]);
				if (!"null".equals(inputArray[1])) {
					if (introduced.isAfter(discontinued) || introduced.equals(discontinued)) {
						throw new InputException("Discontinued is before introduced");
					}
				} else {
					throw new InputException("Discontinued cannot be registered without introduced");
				}
			}
			if (!"null".equals(inputArray[3])) {
				companyId = Long.parseLong(inputArray[3]);
			}
			computer = Optional.ofNullable(new Computer.ComputerBuilder(null).name(name).introduced(introduced)
					.discontinued(discontinued).company(new Company.CompanyBuilder(companyId).build()).build());
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value.");
			showCreateOneComputer();
			computer = createOneComputerAskInput();
		} catch (DateTimeParseException e) {
			System.out.println("You didn't the date in the right format.");
			showCreateOneComputer();
			computer = createOneComputerAskInput();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You didn't enter the right number of argument.");
			showCreateOneComputer();
			computer = createOneComputerAskInput();
		} catch (InputException e) {
			System.out.println(e.getMessage());
			showCreateOneComputer();
			computer = createOneComputerAskInput();
		}

		return computer;
	}

	public static void showUpdateOneComputer() {
		lineSeparator();
		System.out.println(
				"Computer To Update [Id, name, introduced, discontinued, company_id] (Write null if null And Date in format :YYYY-MM-DD) :");

	}

	public static Computer updateOneComputerAskInput() {
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
				if (!("null").equals(inputArray[2])) {
					if (introduced.isAfter(discontinued) || introduced.equals(discontinued)) {
						throw new InputException("Discontinued is before introduced");
					}
				} else {
					throw new InputException("Discontinued cannot be registered without introduced");
				}

			}

			if (!inputArray[4].equals("null")) {
				companyId = Long.parseLong(inputArray[4]);
			}

			computer = new Computer.ComputerBuilder(id).name(name).introduced(introduced).discontinued(discontinued)
					.company(new Company.CompanyBuilder(companyId).build()).build();
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a numerical value.");
			showUpdateOneComputer();
			computer = updateOneComputerAskInput();
		} catch (DateTimeParseException e) {
			System.out.println("You didn't the date in the right format.");
			showUpdateOneComputer();
			computer = updateOneComputerAskInput();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("You didn't enter the right number of argument.");
			showUpdateOneComputer();
			computer = updateOneComputerAskInput();
		} catch (InputException e) {
			System.out.println(e.getMessage());
			showUpdateOneComputer();
			computer = updateOneComputerAskInput();
		}

		return computer;
	}

	public static void showDeleteOneComputer() {
		lineSeparator();
		System.out.print("Computer to delete id: ");

	}

	public static long deleteOneComputerAskInput() {
		long id = 0L;
		try {

			id = Long.parseLong(USER_INPUT.nextLine());

		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value.");
			id = deleteOneComputerAskInput();
		}

		return id;
	}

	public static void validateCreation(Computer compToCreate) {

		lineSeparator();
		System.out.println("Do you validate the creation of " + compToCreate.toString());
		lineSeparator();
		System.out.println("1.Yes");
		System.out.println("2.No");
		lineSeparator();
		enterChoice();

	}

	public static boolean validatoinCreationAskInput(Computer compToCreate) {
		boolean validate = false;
		int choix = 0;
		try {

			choix = Integer.parseInt(USER_INPUT.nextLine());
			if (choix == 1) {
				validate = true;
			} else if (choix == 2) {
				validate = false;
			} else {
				System.out.println("Revalider votre choix");
				validateCreation(compToCreate);
				validate = validatoinCreationAskInput(compToCreate);
			}
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showComputerMenu();
			choix = computerMenuAskInput();
		}

		return validate;
	}

	public static void showPaginationrMenu() {
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

	}

	public static int paginationMenuAskInput() {
		int choix = 0;
		try {

			choix = Integer.parseInt(USER_INPUT.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a numerical value");
			showPaginationrMenu();
			choix = paginationMenuAskInput();
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

	public void computerMenu() {
		int choix = 0;
		computerLoop: while (choix != OPTION_EXIT_COMPUTER) {
			showComputerMenu();
			choix = computerMenuAskInput();
			switch (choix) {

			case OPTION_SEARCH_ALL_COMPUTER:
				cliComputerMenuController.searchAllComputer();
				break;
			case OPTION_SEARCH_ALL_PAGINATION_COMPUTER:
				page.searchAllComputerPagination();
				break;
			case OPTION_SEARCH_BY_ID_COMPUTER:
				cliComputerMenuController.searchByIdComputer();
				break;
			case OPTION_CREATE_COMPUTER:
				cliComputerMenuController.createComputer();
				break;
			case OPTION_UPDATE_COMPUTER:
				cliComputerMenuController.updateComputer();
				break;
			case OPTION_DELETE_COMPUTER:
				cliComputerMenuController.deleteComputer();
				break;
			case OPTION_BACK_TO_MAIN_COMPUTER:
				break computerLoop;
			case OPTION_EXIT_COMPUTER:
				System.exit(0);
				break;
			default:
				System.out.println("Sorry, please enter valid Option");

			}
		}

	}

	public void companyMenu() {
		int choix = 0;
		companyLoop: while (choix != OPTION_EXIT_COMPANY) {
			showCompanyMenu();
			choix = askCompanyMenuInput();
			switch (choix) {

			case OPTION_SEARCH_ALL_COMPANY:
				cliCompanyMenuController.searchAllCompany();
				break;
			case OPTION_SEARCH_ALL_PAGINATION_COMPANY:
				page.searchAllCompanyPageUseDAO();
				break;
			case OPTION_BACK_COMPANY:
				break companyLoop;
			case OPTION_EXIT_COMPANY:
				System.exit(0);
			default:
				System.out.println("Sorry, please enter valid Option");

			}

		}
	}

	public void mainMenu() {
		int choix = 0;
		while (choix != OPTION_EXIT_MAIN) {
			showMainMenu();
			choix = askInputMainMenu();
			switch (choix) {

			case OPTION_COMPANY_MAIN:
				companyMenu();
				break;
			case OPTION_COMPUTER_MAIN:
				computerMenu();
				break;
			case OPTION_EXIT_MAIN:
				System.exit(0);
				break;
			default:
				System.out.println("Sorry, please enter valid Option");

			}
		}

	}

}
