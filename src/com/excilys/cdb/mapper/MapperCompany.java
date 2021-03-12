package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Company;

public class MapperCompany {

	/**
	 * Default constructor.
	 */
	public MapperCompany() {

	}

	public Company mapSqlToJava(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		return company;
	}
}
