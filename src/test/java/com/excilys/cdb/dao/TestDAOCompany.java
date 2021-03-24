package com.excilys.cdb.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.model.Company;

public class TestDAOCompany extends DataSourceDBUnitTest {

	@Test
	public void testInsertThenSearchCompanyDAO() throws Exception {
		DBConnexion dbConnexionMock = Mockito.mock(DBConnexion.class);
		Connection conn = getDataSource().getConnection();
		Mockito.when(dbConnexionMock.getConnection()).thenReturn(conn);
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		companyDAOImpl.setDbConnexion(dbConnexionMock);
		conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
		List<Company> companies = companyDAOImpl.searchAll();
		assertEquals(13, companies.size());
	}

	@Test
	public void testSearchAllCompanyDAO() throws Exception {
		DBConnexion dbConnexionMock = Mockito.mock(DBConnexion.class);
		Connection conn = getDataSource().getConnection();
		Mockito.when(dbConnexionMock.getConnection()).thenReturn(conn);
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		companyDAOImpl.setDbConnexion(dbConnexionMock);
		List<Company> companies = companyDAOImpl.searchAll();
		assertEquals(12, companies.size());

	}
}
