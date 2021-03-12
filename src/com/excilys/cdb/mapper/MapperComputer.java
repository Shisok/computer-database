package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.model.Computer;

public class MapperComputer {

	public Computer mapFromResultSet(ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		if (resultSet.getDate("introduced") != null) {
			computer.setIntroduced(resultSet.getDate("introduced").toLocalDate());
		}
		if (resultSet.getDate("discontinued") != null) {
			computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
		}
		if (resultSet.getObject("company_id") == null) {
			computer.setCompanyId(null);
		} else {
			computer.setCompanyId(resultSet.getLong("company_id"));
		}
		return computer;
	}
}
