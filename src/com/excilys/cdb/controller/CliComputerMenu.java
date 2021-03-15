package com.excilys.cdb.controller;

import com.excilys.cdb.service.ComputerService;

public class CliComputerMenu {

	ComputerService computerService;

	/**
	 * Default Constructor.
	 */
	public CliComputerMenu() {
		super();
		this.computerService = new ComputerService();

	}

	public void searchAllComputer() {

		computerService.searchAllComputer();
	}

	public void searchByIdComputer() {
		computerService.searchByIdComputer();
	}

	public void createComputer() {
		computerService.createComputer();
	}

	public void updateComputer() {
		computerService.updateComputer();
	}

	public void deleteComputer() {
		computerService.deleteComputer();
	}

}
