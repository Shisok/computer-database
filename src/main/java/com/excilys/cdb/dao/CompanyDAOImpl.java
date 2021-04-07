package com.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.RowMapperCompany;
import com.excilys.cdb.model.Company;

@EnableTransactionManagement
@Repository
public class CompanyDAOImpl {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private JdbcTemplate jdbcTemplate;

	private RowMapperCompany rowMapperCompany;

	public CompanyDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate,
			RowMapperCompany rowMapperCompany) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapperCompany = rowMapperCompany;
	}

	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	private static final String SQL_ALL_COMPANY = "SELECT id,name FROM company ORDER BY id";

	private static final String SQL_ALL_COMPANY_PAGINATION = "SELECT id,name From company ORDER BY id LIMIT :limit OFFSET :offset ;";
	private static final String SQL_DELETE = "DELETE FROM company WHERE id=:id;";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id=:id;";

	public List<Company> searchAll() {
		List<Company> computers = new ArrayList<>();
		try {

			computers = jdbcTemplate.query(SQL_ALL_COMPANY, rowMapperCompany);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return computers;
	}

	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companies = new ArrayList<>();
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("limit", OBJECT_NUMBER_PER_PAGE);
			params.addValue("offset", page * OBJECT_NUMBER_PER_PAGE);

			companies = namedParameterJdbcTemplate.query(SQL_ALL_COMPANY_PAGINATION, params, rowMapperCompany);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return companies;

	}

	@Transactional
	public void delete(Long id) throws DAOException {

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			jdbcTemplate.update(SQL_DELETE_COMPUTER, params);
			MapSqlParameterSource paramsBug = new MapSqlParameterSource();
			paramsBug.addValue("id", 4);
			jdbcTemplate.update(SQL_DELETE, paramsBug);
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
