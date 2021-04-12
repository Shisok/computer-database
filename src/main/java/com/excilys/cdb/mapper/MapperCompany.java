package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.model.Company;

@Component
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

	public List<CompanyDTO> mapFromModelListToDTOList(List<Company> listCompanies) {

		List<CompanyDTO> listCompaniesDTO = listCompanies.stream().map(c -> mapFromModelToDTO(c))
				.collect(Collectors.toList());

		return listCompaniesDTO;
	}

}
