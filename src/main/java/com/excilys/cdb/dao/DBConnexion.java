package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.exception.DAOConfigurationException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnexion {
	private static DBConnexion instance;
	private HikariDataSource ds;

	private DBConnexion(HikariDataSource ds) {
		this.ds = ds;
	}

	public static DBConnexion getInstance() throws DAOConfigurationException {
		if (instance == null) {
			instance = new DBConnexion(
					new HikariDataSource(new HikariConfig("/com/excilys/cdb/dao/datasource.properties")));
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}