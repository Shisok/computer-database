package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.exception.DAOConfigurationException;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.view.CliMenu;

public class ComputerService {

	public List<Computer> searchAllComputer() {
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			return computerDAOImpl.searchAll();
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Computer>();
	}

	public Optional<Computer> searchByIdComputer(Long idToSearch) {
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			Optional<Computer> compSearched = computerDAOImpl.search(idToSearch);
			return compSearched;
		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return Optional.empty();
	}

	public boolean createComputer(Computer compToCreate) {
		boolean success = false;
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();

			computerDAOImpl.create(compToCreate);
			success = true;

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return success;
	}

	public boolean updateComputer(Computer compToUpdate) {
		boolean success = false;
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			CliMenu.showUpdateOneComputer();
			computerDAOImpl.update(compToUpdate);
			success = true;

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return success;
	}

	public boolean deleteComputer(Long compToDeleteID) {
		boolean success = false;
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			computerDAOImpl.delete(compToDeleteID);
			success = true;

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return success;
	}

	public int countComputer() {
		int nbComputer = 0;
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			nbComputer = computerDAOImpl.searchAllCount();

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return nbComputer;
	}

	public int searchNameCount(String name) {
		int nbComputer = 0;
		try {

			ComputerDAOImpl computerDAOImpl = new ComputerDAOImpl();
			nbComputer = computerDAOImpl.searchNameCount(name);

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return nbComputer;
	}

}
