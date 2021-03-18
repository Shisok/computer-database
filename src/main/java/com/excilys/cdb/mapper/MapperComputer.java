package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.dto.ComputerDTOList;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class MapperComputer {

	public Computer mapFromResultSet(ResultSet resultSet) throws SQLException {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company = new Company.CompanyBuilder(null).build();

		Computer computer;
		if (resultSet.getDate("introduced") != null) {
			introduced = resultSet.getDate("introduced").toLocalDate();
		}
		if (resultSet.getDate("discontinued") != null) {
			discontinued = resultSet.getDate("discontinued").toLocalDate();
		}
		if (resultSet.getObject("company_id") != null) {
			company = new Company.CompanyBuilder(resultSet.getLong("company_id"))
					.name(resultSet.getString("companyName")).build();
		}
		computer = new Computer.ComputerBuilder(resultSet.getLong("id")).name(resultSet.getString("name"))
				.introduced(introduced).discontinued(discontinued).company(company).build();

		return computer;
	}

	public ComputerDTOList mapFromModelToDTOList(Computer computer) {
		ComputerDTOList computerDTO = new ComputerDTOList();
		computerDTO.setId(computer.getId().toString());
		computerDTO.setName(computer.getName());

		if (computer.getDiscontinued() != null) {
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		}
		if (computer.getIntroduced() != null) {
			computerDTO.setIntroduced(computer.getIntroduced().toString());
		}
		if (computer.getCompany().getName() != null) {
			computerDTO.setCompanyName(computer.getCompany().getName());
		}

		return computerDTO;
	}

	public Computer mapFromDTOAddToModel(ComputerDTOAdd computerDTOAdd) {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company = new Company.CompanyBuilder(null).build();
		Computer computer = new Computer.ComputerBuilder(null).build();
		if (computerDTOAdd.getIntroduced() != null && computerDTOAdd.getIntroduced().compareTo("") != 0) {
			discontinued = LocalDate.parse(computerDTOAdd.getIntroduced());
		}

		if (computerDTOAdd.getDiscontinued() != null && computerDTOAdd.getDiscontinued().compareTo("") != 0) {
			introduced = LocalDate.parse(computerDTOAdd.getDiscontinued());
		}

		if (computerDTOAdd.getCompanyId() != null && computerDTOAdd.getCompanyId().compareTo("") != 0) {
			company = new Company.CompanyBuilder(Long.parseLong(computerDTOAdd.getCompanyId())).build();
		}
		computer = new Computer.ComputerBuilder(null).name(computerDTOAdd.getComputerName()).introduced(introduced)
				.discontinued(discontinued).company(company).build();
		return computer;
	}
}
