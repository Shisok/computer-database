package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;

public class MapperCompany {

	public Company mapFromResultSet(ResultSet resultSet) throws SQLException {

		Company company = new Company.CompanyBuilder(resultSet.getLong("id")).name(resultSet.getString("name")).build();

		return company;
	}

	public CompanyDTO mapFromModelToDTO(Company company) {

		CompanyDTO companyDTO = new CompanyDTO.CompanyDTOBuilder(company.getId().toString()).name(company.getName())
				.build();

		return companyDTO;
	}

}
