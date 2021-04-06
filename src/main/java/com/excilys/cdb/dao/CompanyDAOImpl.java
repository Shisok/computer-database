package com.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.RowMapperCompany;
import com.excilys.cdb.model.Company;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class CompanyDAOImpl {

	@Autowired
	private HikariDataSource dataSource;
	@Autowired
	RowMapperCompany rowMapperCompany;

	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	private static final String SQL_ALL_COMPANY = "SELECT id,name FROM company ORDER BY id";

	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT :limit OFFSET :offset ;";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=:id;";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id=:id;";

	public List<Company> searchAll() {
		List<Company> computers = new ArrayList<>();
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			computers = jdbcTemplate.query(SQL_ALL_COMPANY, rowMapperCompany);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return computers;
	}

	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companies = new ArrayList<>();
		try {
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("limit", OBJECT_NUMBER_PER_PAGE);
			params.addValue("offset", page * OBJECT_NUMBER_PER_PAGE);

			companies = jdbcTemplate.query(SQL_ALL_COMPANY_PAGINATION, params, rowMapperCompany);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return companies;

	}

//	public void delete(Long id) throws DAOException {
//
//		try (Connection connexion = dataSource.getConnection();) {
//			connexion.setAutoCommit(false);
//			try (PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_DELETE);
//					PreparedStatement preparedStatementComputer = createPrepaStateWithCompId(connexion, id,
//							SQL_DELETE_COMPUTER);) {
//				preparedStatementComputer.executeUpdate();
//				int statut = preparedStatement.executeUpdate();
//				if (statut == 0) {
//					throw new DAOException(
//							"Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
//				}
//			} catch (SQLException e) {
//				connexion.rollback();
//				LoggerCdb.logError(this.getClass(), e);
//				throw new DAOException(
//						"Une erreur est survenue lors de la suppression de la companie dans la base de donnée.");
//			} finally {
//				connexion.setAutoCommit(true);
//			}
//		} catch (SQLException e1) {
//			LoggerCdb.logError(this.getClass(), e1);
//		}
//
//	}
//
//	private static PreparedStatement createPrepaStateWithCompId(Connection connexion, Long id, String sql)
//			throws SQLException {
//		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
//		preparedStatement.setLong(1, id);
//		return preparedStatement;
//	}

	@Transactional(rollbackFor = { Exception.class })
	public void delete(Long id) throws DAOException {

		try {
			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			jdbcTemplate.update(SQL_DELETE_COMPUTER, params);
//			MapSqlParameterSource paramsBug = new MapSqlParameterSource();
//			paramsBug.addValue("id", 4);
//			jdbcTemplate.update(SQL_DELETE, paramsBug);
			int statut = jdbcTemplate.update(SQL_DELETE, params);

			if (statut == 0) {
				throw new DAOException("Échec de la suppression de la company, aucune ligne ajoutée dans la table.");
			}
			throw new DAOException("test");
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

}
