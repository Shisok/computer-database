package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;

public class CompanyDAOImpl {

	private DBConnexion dbConnexion;
	private MapperCompany mapperCompany;
	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	private static final String SQL_ALL_COMPANY = "SELECT id,name FROM company";
	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT ?,? ;";

	public CompanyDAOImpl() {
		this.dbConnexion = DBConnexion.getInstance();
		this.mapperCompany = new MapperCompany();

	}

	public List<Company> searchAll() {
		List<Company> companies = new ArrayList<>();

		try (Connection connexion = dbConnexion.getConnection();
				Statement statement = connexion.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_ALL_COMPANY)) {

			while (resultSet.next()) {
				Company company = mapperCompany.mapFromResultSet(resultSet);
				companies.add(company);
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);

		}

		return companies;
	}

	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companys = new ArrayList<>();

		int offset = page * OBJECT_NUMBER_PER_PAGE;
		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForPagination(connexion, offset);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				Company company = mapperCompany.mapFromResultSet(resultSet);
				companys.add(company);
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

		return companys;
	}

	private static PreparedStatement createPrepaStateForPagination(Connection connexion, int offset)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_ALL_COMPANY_PAGINATION,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, offset);
		preparedStatement.setInt(2, OBJECT_NUMBER_PER_PAGE);
		return preparedStatement;
	}

}
