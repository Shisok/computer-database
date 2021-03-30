package com.excilys.cdb.dao.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;

public class TestMapperCompany {

	@Test
	public void testMapFromResultSet() {
		try {
			ResultSet rsMock = Mockito.mock(ResultSet.class);
			MapperCompany mapperCompany = new MapperCompany();
			Mockito.when(rsMock.getLong("id")).thenReturn(1L);
			Mockito.when(rsMock.getString("name")).thenReturn("test");
			Company company = mapperCompany.mapFromResultSet(rsMock);
			Company companyExpected = new Company.CompanyBuilder(1L).name("test").build();
			assertEquals(company, companyExpected);
		} catch (SQLException e1) {
			LoggerCdb.logError(this.getClass(), e1);
		}
	}

	@Test
	public void testMapFromModelToDTO() {

		MapperCompany mapperCompany = new MapperCompany();
		Company company = new Company.CompanyBuilder(1L).name("test").build();
		CompanyDTO companyDTO = mapperCompany.mapFromModelToDTO(company);
		CompanyDTO companyDTOExpected = new CompanyDTO.CompanyDTOBuilder("1").name("test").build();
		assertEquals(companyDTO.getId(), companyDTOExpected.getId());
		assertEquals(companyDTO.getName(), companyDTOExpected.getName());

	}

}
