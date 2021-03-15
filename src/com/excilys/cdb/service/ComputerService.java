package com.excilys.cdb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;
import com.excilys.cdb.view.InputException;

public class ComputerService {

	public void searchAllComputer() {

		DBConnexion daoFactory = DBConnexion.getInstance();
		ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
		for (Computer comp : computerDAOImpl.searchAll()) {
			System.out.println(comp.toString());
		}
	}

	public void searchByIdComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showSearchOneComputer();
			Long idToSearch = CliMenu.searchOneComputerAskInput();
			Optional<Computer> compSearched = computerDAOImpl.search(idToSearch);
			System.out.println(compSearched.orElseThrow().toString());
		} catch (NoSuchElementException e) {
			System.out.println("L'ordinateur n'existe pas");
			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		}
	}

	public void createComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showCreateOneComputer();
			Optional<Computer> compToCreate = CliMenu.createOneComputerAskInput();
			CliMenu.validateCreation(compToCreate);
			if (CliMenu.validatoinCreationAskInput(compToCreate)) {
				computerDAOImpl.create(compToCreate.orElseThrow());
				System.out.println(compToCreate.toString() + " created");
			}

		} catch (NoSuchElementException e) {
			System.out.println("The computer cannot be created");
		}
	}

	public void updateComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showUpdateOneComputer();
			Computer compToUpdate = CliMenu.updateOneComputerAskInput();
			computerDAOImpl.update(compToUpdate);
			System.out.println(compToUpdate.toString() + " updated");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		}
	}

	public void deleteComputer() {
		try {

			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showDeleteOneComputer();
			Long compToDeleteID = CliMenu.deleteOneComputerAskInput();

			computerDAOImpl.delete(compToDeleteID);
			System.out.println("Computer " + compToDeleteID + ": deleted");
		} catch (InputException e) {
			System.out.println(e.getMessage());
			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		}
	}

	public static int showMainMenu() {
		CliMenu.showComputerMenu();
		return CliMenu.computerMenuAskInput();

	}

}
