package com.excilys.cdb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;

@Component
public class ComputerController {
	@Autowired
	ComputerService computerService;

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
