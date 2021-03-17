package com.excilys.cdb.controller;

import java.util.List;
import java.util.Optional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

public class ComputerController {

	ComputerService computerService;

	/**
	 * Default Constructor.
	 */
	public ComputerController() {
		super();
		this.computerService = new ComputerService();

	}

	public List<Computer> searchAllComputer() {

		return computerService.searchAllComputer();
	}

	public Optional<Computer> searchByIdComputer(Long idToSearch) {
		return computerService.searchByIdComputer(idToSearch);
	}

	public boolean createComputer(Computer compToCreate) {
		return computerService.createComputer(compToCreate);
	}

	public boolean updateComputer(Computer compToUpdate) {
		return computerService.updateComputer(compToUpdate);
	}

	public boolean deleteComputer(Long compToDeleteID) {
		return computerService.deleteComputer(compToDeleteID);
	}

}
