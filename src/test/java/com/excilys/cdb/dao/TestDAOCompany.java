package com.excilys.cdb.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import com.excilys.cdb.model.Company;

public class TestDAOCompany extends DataSourceDBUnitTest {

	@Test
	public void testInsertThenSearchCompanyDAO() throws Exception {

		Connection conn = getDataSource().getConnection();

		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();

		conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
		List<Company> companies = companyDAOImpl.searchAll();
		assertEquals(13, companies.size());
	}

	@Test
	public void testSearchAllCompanyDAO() throws Exception {
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		List<Company> companies = companyDAOImpl.searchAll();
		assertEquals(12, companies.size());

	}
}
