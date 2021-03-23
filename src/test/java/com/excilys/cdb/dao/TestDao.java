package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;

public class TestDao {
	@Test
	public void testInsert() throws Exception {
		MapperCompany mapperCompany = new MapperCompany();
		List<Company> companies = new ArrayList<>();
		DataSourceDBUnitTest test = new DataSourceDBUnitTest();
		Connection conn = test.getDataSource().getConnection();
		test.getSetUpOperation();
		conn.createStatement().executeUpdate("INSERT INTO COMPANY (id,name) VALUES ( 42,'Research In Motion')");
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM COMPANY");
		while (rs.next()) {
			Company company = mapperCompany.mapFromResultSet(rs);
			companies.add(company);
		}
		companies.stream().forEach(c -> System.out.println(c));
	}
}
