package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class MapperComputer {

	public Computer mapFromResultSet(ResultSet resultSet) throws SQLException {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company = new Company.CompanyBuilder(null).build();

		Computer computer;
		if (resultSet.getDate("discontinued") != null) {
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
}
