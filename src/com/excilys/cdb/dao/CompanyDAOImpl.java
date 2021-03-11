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

public class CompanyDAOImpl implements CompanyDAO {

	private DAOFactory daoFactory;

	private static final String SQL_ALL_COMPANY = "SELECT * FROM company";
	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT ?,? ;";

	public CompanyDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le mapping)
	 * entre une ligne issue de la table des companies (un ResultSet) et un bean
	 * Company.
	 */
	private static Company map(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setId(resultSet.getLong("id"));
		company.setName(resultSet.getString("name"));
		return company;
	}

	@Override
	public List<Company> searchAll() throws DAOException {
		List<Company> companies = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_COMPANY, false);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
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

	@Override
	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companys = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		int offset = page * 10;
		try {

			/* Récupération d'une connexion depuis la Factory */
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
