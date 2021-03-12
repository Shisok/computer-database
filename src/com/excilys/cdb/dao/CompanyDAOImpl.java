package com.excilys.cdb.dao;

import static com.excilys.cdb.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.excilys.cdb.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;

public class CompanyDAOImpl {

	private DBConnexion daoFactory;

	private static final String SQL_ALL_COMPANY = "SELECT * FROM company";
	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT ?,? ;";

	public CompanyDAOImpl(DBConnexion daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static Company map(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		return company;
	}

	public List<Company> searchAll() {
		List<Company> companies = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_COMPANY, false);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				company = map(resultSet);
				companies.add(company);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return companies;
	}

	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companys = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		int offset = page * 10;
		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_COMPANY_PAGINATION, false, offset, 10);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				company = map(resultSet);
				companys.add(company);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return companys;
	}

}
