package com.excilys.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

@Component
public class ComputerController {

	ComputerService computerService;

	public ComputerController(ComputerService computerService) {

		this.computerService = computerService;
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
