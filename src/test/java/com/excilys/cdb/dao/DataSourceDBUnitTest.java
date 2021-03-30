package com.excilys.cdb.dao;

import static org.dbunit.Assertion.assertEqualsIgnoreCols;

import java.io.InputStream;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

public class DataSourceDBUnitTest extends DataSourceBasedDBTestCase {
	@Override
	protected DataSource getDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(
				"jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:com/excilys/cdb/dao/schema.sql'");
		dataSource.setUser("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder()
				.build(getClass().getClassLoader().getResourceAsStream("com/excilys/cdb/dao/data.xml"));
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
	public void testDbUnitInitDataCorrespondance() throws Exception {

		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("COMPUTER");
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("COMPUTER");
		assertEquals(expectedTable.getRowCount(), actualTable.getRowCount());
		getTearDownOperation();
	}

	@Test
	public void testInsertOnDB() throws Exception {

		try (InputStream is = getClass().getClassLoader()
				.getResourceAsStream("com/excilys/cdb/dao/dataExpectedInsertOnDB.xml")) {
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
			ITable expectedTable = expectedDataSet.getTable("COMPANY");
			Connection conn = getDataSource().getConnection();
			conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
			ITable actualData = getConnection().createQueryTable("result_name",
					"SELECT * FROM COMPANY WHERE name='Research In Motion'");
			assertEqualsIgnoreCols(expectedTable, actualData, new String[] {"id"});
		}

	}

}
