package com.excilys.cdb.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DAOConfigurationException;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;
import com.excilys.cdb.view.InputException;

public class ComputerService {

	public void searchAllComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			computerDAOImpl.searchAll().stream().forEach(c -> System.out.println(c.toString()));
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	public void searchByIdComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showSearchOneComputer();
			Long idToSearch = CliMenu.searchOneComputerAskInput();
			Optional<Computer> compSearched = computerDAOImpl.search(idToSearch);
			System.out.println(compSearched.orElseThrow(() -> new NoSuchElementException()).toString());
		} catch (NoSuchElementException e) {
			LoggerCdb.logError(this.getClass(), e);

			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	public void createComputer() {
		try {
			DBConnexion daoFactory = DBConnexion.getInstance();
			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl(daoFactory);
			CliMenu.showCreateOneComputer();
			Optional<Computer> compToCreateOptionnal = CliMenu.createOneComputerAskInput();
			Computer compToCreate = compToCreateOptionnal.orElseThrow(() -> new NoSuchElementException());
			CliMenu.validateCreation(compToCreate);
			if (CliMenu.validatoinCreationAskInput(compToCreate)) {
				computerDAOImpl.create(compToCreate);
				System.out.println(compToCreate.toString() + " created");
			}

		} catch (NoSuchElementException e) {
			LoggerCdb.logError(this.getClass(), e);

		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
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
			LoggerCdb.logError(this.getClass(), e);
			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
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
			LoggerCdb.logError(this.getClass(), e);
			CliMenu.showComputerMenu();
			CliMenu.computerMenuAskInput();
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
	}

	public static int showMainMenu() {
		CliMenu.showComputerMenu();
		return CliMenu.computerMenuAskInput();

	}

}
