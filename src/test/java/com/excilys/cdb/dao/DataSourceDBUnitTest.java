package com.excilys.cdb.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.cdb.model.Company;

public class DataSourceDBUnitTest extends DataSourceBasedDBTestCase {
	@Override
	protected DataSource getDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:schema.sql'");
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("data.xml"));
	}

	@Override
	protected DatabaseOperation getSetUpOperation() {
		return DatabaseOperation.REFRESH;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() {
		return DatabaseOperation.DELETE_ALL;
	}

	@Test
	public void testDbUnitInit() throws Exception {

		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("COMPUTER");
		IDataSet databaseDataSet = getConnection().createDataSet();

		ITable actualTable = databaseDataSet.getTable("COMPUTER");
		assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());
		getTearDownOperation();
	}

//	@Test
//	public void testExample() throws Exception {
//		getSetUpOperation();
//		IDataSet expectedDataSet = getDataSet();
//		ITable expectedTable = expectedDataSet.getTable("CLIENTS");
//		IDataSet databaseDataSet = getConnection().createDataSet();
//		ITable actualTable = databaseDataSet.getTable("CLIENTS");
//		assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());
//		getTearDownOperation();
//
//	}
	@Test
	public void testInsert() throws Exception {

		try (InputStream is = getClass().getClassLoader().getResourceAsStream("dataExpected.xml")) {
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
			ITable expectedTable = expectedDataSet.getTable("COMPANY");

			Connection conn = getDataSource().getConnection();

			conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
			ITable actualData = getConnection().createQueryTable("result_name",
					"SELECT * FROM COMPANY WHERE name='Research In Motion'");
			org.dbunit.Assertion.assertEqualsIgnoreCols(expectedTable, actualData, new String[] { "id" });
		}

	}

	@Test
	public void testInsertCompanyDAO() throws Exception {

		DBConnexion dbConnexionMock = Mockito.mock(DBConnexion.class);
		Connection conn = getDataSource().getConnection();
		Mockito.when(dbConnexionMock.getConnection()).thenReturn(conn);

		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		companyDAOImpl.setDbConnexion(dbConnexionMock);
		conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
		List<Company> companies = companyDAOImpl.searchAll();
		assertEquals(13, companies.size());
	}

}
