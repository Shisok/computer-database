package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

	private static HikariConfig config = new HikariConfig("datasource.properties");
	private static HikariDataSource ds = new HikariDataSource(config);

	private DataSource() {
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}