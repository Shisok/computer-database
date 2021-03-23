package com.excilys.cdb.dao;

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
	public void testDbUnit() throws Exception {
		getSetUpOperation();
		IDataSet expectedDataSet = getDataSet();
		ITable expectedTable = expectedDataSet.getTable("COMPANY");
		IDataSet databaseDataSet = getConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("COMPANY");
		assertEquals(expectedTable, actualTable);
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
}
