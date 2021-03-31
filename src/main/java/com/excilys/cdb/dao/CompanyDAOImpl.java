package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class CompanyDAOImpl {

	@Autowired
	private HikariDataSource dataSource;
	@Autowired
	private MapperCompany mapperCompany;

	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	private static final String SQL_ALL_COMPANY = "SELECT id,name FROM company ORDER BY id";

	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT ? OFFSET ? ;";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=?;";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id=?;";

	public List<Company> searchAll() {
		List<Company> companies = new ArrayList<>();

		try (Connection connexion = dataSource.getConnection();
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
		try (Connection connexion = dataSource.getConnection();
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

		preparedStatement.setInt(1, OBJECT_NUMBER_PER_PAGE);
		preparedStatement.setInt(2, offset);
		return preparedStatement;
	}

	public void delete(Long id) throws DAOException {

		try (Connection connexion = dataSource.getConnection();) {
			connexion.setAutoCommit(false);
			try (PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_DELETE);
					PreparedStatement preparedStatementComputer = createPrepaStateWithCompId(connexion, id,
							SQL_DELETE_COMPUTER);) {
				preparedStatementComputer.executeUpdate();
				int statut = preparedStatement.executeUpdate();
				if (statut == 0) {
					throw new DAOException(
							"Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
				}
			} catch (SQLException e) {
				connexion.rollback();
				LoggerCdb.logError(this.getClass(), e);
				throw new DAOException(
						"Une erreur est survenue lors de la suppression de la companie dans la base de donnée.");
			} finally {
				connexion.setAutoCommit(true);
			}
		} catch (SQLException e1) {
			LoggerCdb.logError(this.getClass(), e1);
		}

	}

	private static PreparedStatement createPrepaStateWithCompId(Connection connexion, Long id, String sql)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		return preparedStatement;
	}

}
