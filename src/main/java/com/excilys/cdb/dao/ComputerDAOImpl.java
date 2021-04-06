package com.excilys.cdb.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.RowMapperComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

@Repository
public class ComputerDAOImpl {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	RowMapperComputer rowMapperComputer;

	private static final String SQL_UPDATE = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:company_id WHERE id=:id;";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=:id;";
	private static final String SQL_SELECT = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued ,company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id = :id;";

	private static final String SQL_ALL_COMPUTER = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY id;";
	private static final String SQL_ALL_COMPUTER_PAGINATION = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY ORDERATTRIBUTE ORDERSORT LIMIT :limit OFFSET :offset ;";
	private static final String SQL_COUNT_ALL_COMPUTER = "SELECT COUNT(computer.id) as nbComputer  FROM computer LEFT JOIN company ON computer.company_id=company.id;";
	private static final String SQL_SEARCH_BY_NAME_COMPA_COMPU = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued ,company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE :nameComputer OR company.name LIKE :nameCompany ORDER BY ORDERATTRIBUTE ORDERSORT LIMIT :limit OFFSET :offset;";
	private static final String SQL_SEARCH_BY_NAME_COUNT = "SELECT COUNT(computer.id) as nbComputer FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE :nameComputer OR company.name LIKE :nameCompany ;";

	public void create(Computer computer) throws DAOException {
		try {

			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
			simpleJdbcInsert.withTableName("computer").usingGeneratedKeyColumns("id");
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("name", computer.getName());
			params.addValue("introduced", computer.getIntroduced());
			params.addValue("discontinued", computer.getDiscontinued());
			params.addValue("company_id", computer.getCompany().getId());
			params.addValue("id", computer.getId());
			Number id = simpleJdbcInsert.executeAndReturnKey(params);
			System.out.println(id);
			if (id != null) {
				computer.setId(id.longValue());
			} else {
				throw new DAOException("Échec de la création de l'ordinateur en base, aucun ID auto-généré retourné.");
			}

		} catch (DataIntegrityViolationException e) {
			if (SQLIntegrityConstraintViolationException.class == e.getCause().getClass()) {
				throw new DAOException("An error occured because the company doesn't exist.");
			} else if (MysqlDataTruncation.class == e.getCause().getClass()) {
				throw new DAOException("An error occured because date cannot be bellow 1970-01-01.");
			} else {
				LoggerCdb.logError(this.getClass(), e);
			}
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		} catch (DAOException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

	public void update(Computer computer) throws DAOException {

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("name", computer.getName());
			params.addValue("introduced", computer.getIntroduced());
			params.addValue("discontinued", computer.getDiscontinued());
			params.addValue("company_id", computer.getCompany().getId());
			params.addValue("id", computer.getId());
			int statut = namedParameterJdbcTemplate.update(SQL_UPDATE, params);
			if (statut == 0) {
				throw new DAOException("Échec de la modification de l'ordinateur.");
			}
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		} catch (DAOException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

	public void delete(Long id) throws DAOException {

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			int statut = namedParameterJdbcTemplate.update(SQL_DELETE, params);
			if (statut == 0) {
				throw new DAOException("Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
			}
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

	public Optional<Computer> search(Long id) throws DAOException {
		Optional<Computer> computer = Optional.empty();

		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			computer = Optional
					.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_SELECT, params, rowMapperComputer));

		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

		return computer;
	}

	public List<Computer> searchAll() {
		List<Computer> computers = new ArrayList<>();
		try {

			computers = jdbcTemplate.query(SQL_ALL_COMPUTER, rowMapperComputer);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return computers;
	}

	public List<Computer> searchAllPagination(Page<Computer> page) {
		List<Computer> computers = new ArrayList<>();
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("limit", page.getObjetPerPage());
			params.addValue("offset", page.getObjetPerPage() * page.getPageInt());

			computers = namedParameterJdbcTemplate.query(SQL_ALL_COMPUTER_PAGINATION
					.replace("ORDERATTRIBUTE", page.getOrderAttribute()).replace("ORDERSORT", page.getOrderSort()),
					params, rowMapperComputer);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return computers;

	}

	public int searchAllCount() {
		int nbComputer = 0;
		try {

			nbComputer = jdbcTemplate.queryForObject(SQL_COUNT_ALL_COMPUTER, Integer.class);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return nbComputer;
	}

	public int searchNameCount(String name) throws DAOException {
		int nbComputer = 0;
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("nameComputer", "%" + name + "%");
			params.addValue("nameCompany", "%" + name + "%");
			nbComputer = namedParameterJdbcTemplate.queryForObject(SQL_SEARCH_BY_NAME_COUNT, params, Integer.class);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return nbComputer;
	}

	public List<Computer> searchNamePagination(Page<Computer> page, String name) throws DAOException {
		List<Computer> computers = new ArrayList<>();
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("limit", page.getObjetPerPage());
			params.addValue("offset", page.getObjetPerPage() * page.getPageInt());
			params.addValue("nameComputer", "%" + name + "%");
			params.addValue("nameCompany", "%" + name + "%");

			computers = namedParameterJdbcTemplate.query(SQL_SEARCH_BY_NAME_COMPA_COMPU
					.replace("ORDERATTRIBUTE", page.getOrderAttribute()).replace("ORDERSORT", page.getOrderSort()),
					params, rowMapperComputer);
		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return computers;
	}

}
