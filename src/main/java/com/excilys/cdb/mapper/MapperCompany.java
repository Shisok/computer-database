package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

public class MapperCompany {

	public Company mapFromResultSet(ResultSet resultSet) throws SQLException {

		Company company = new Company.CompanyBuilder(resultSet.getLong("id")).name(resultSet.getString("name")).build();

		return company;
	}
}
