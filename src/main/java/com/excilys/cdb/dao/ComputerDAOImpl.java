package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class ComputerDAOImpl {
	@Autowired
	private HikariDataSource dataSource;
	@Autowired
	private MapperComputer mapperComputer;

	private static final String SQL_INSERT = "INSERT INTO computer ( name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?;";
	private static final String SQL_SELECT = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued ,company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id = ?;";

	private static final String SQL_ALL_COMPUTER = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY id;";
	private static final String SQL_ALL_COMPUTER_PAGINATION = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id ORDER BY ORDERATTRIBUTE ORDERSORT LIMIT ? OFFSET ?;";
	private static final String SQL_COUNT_ALL_COMPUTER = "SELECT COUNT(computer.id) as nbComputer  FROM computer LEFT JOIN company ON computer.company_id=company.id;";
	private static final String SQL_SEARCH_BY_NAME_COMPA_COMPU = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued ,company.id as company_id, company.name as companyName  FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ORDERATTRIBUTE ORDERSORT LIMIT ? OFFSET ?;";
	private static final String SQL_SEARCH_BY_NAME_COUNT = "SELECT COUNT(computer.id) as nbComputer FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? OR company.name LIKE ? ;";

	public void create(Computer computer) throws DAOException {

		ResultSet valeursAutoGenerees = null;

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForCreate(connexion, computer);) {
			LoggerCdb.logInfo(getClass(), connexion.toString());
			LoggerCdb.logInfo(getClass(), dataSource.toString());
			LoggerCdb.logInfo(getClass(), mapperComputer.toString());
			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("Échec de la création de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {

				computer.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException("Échec de la création de l'ordinateur en base, aucun ID auto-généré retourné.");
			}
		} catch (MysqlDataTruncation e) {

			throw new DAOException("An error occured because date cannot be bellow 1970-01-01.");
		} catch (SQLIntegrityConstraintViolationException e) {

			throw new DAOException("An error occured because the company doesn't exist.");

		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

	private static PreparedStatement createPrepaStateForCreate(Connection connexion, Computer computer)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, computer.getName());
		preparedStatement.setObject(2, computer.getIntroduced());
		preparedStatement.setObject(3, computer.getDiscontinued());
		preparedStatement.setObject(4, computer.getCompany().getId());
		return preparedStatement;
	}

	public void update(Computer computer) throws DAOException {

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForUpdate(connexion, computer);) {

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la mise à jour de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		} catch (DAOException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

	private static PreparedStatement createPrepaStateForUpdate(Connection connexion, Computer computer)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_UPDATE);
		preparedStatement.setString(1, computer.getName());
		preparedStatement.setObject(2, computer.getIntroduced());
		preparedStatement.setObject(3, computer.getDiscontinued());
		preparedStatement.setObject(4, computer.getCompany().getId());
		preparedStatement.setLong(5, computer.getId());
		return preparedStatement;
	}

	public void delete(Long id) throws DAOException {

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_DELETE);) {

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);

		}

	}

	public Optional<Computer> search(Long id) throws DAOException {
		Optional<Computer> computer = Optional.empty();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			if (resultSet.next()) {
				computer = Optional.ofNullable(mapperComputer.mapFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

		return computer;
	}

	private static PreparedStatement createPrepaStateWithCompId(Connection connexion, Long id, String sql)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
		preparedStatement.setLong(1, id);
		return preparedStatement;
	}

	public List<Computer> searchAll() throws DAOException {
		List<Computer> computers = new ArrayList<>();

		try (Connection connexion = dataSource.getConnection();
				Statement statement = connexion.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_ALL_COMPUTER)) {

			while (resultSet.next()) {

				Computer computer = mapperComputer.mapFromResultSet(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);

		}

		return computers;
	}

	public List<Computer> searchAllPagination(Page<Computer> page) {
		List<Computer> computers = new ArrayList<>();

		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForPagination(connexion, page);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				Computer computer = mapperComputer.mapFromResultSet(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

		return computers;
	}

	private static PreparedStatement createPrepaStateForPagination(Connection connexion, Page<Computer> page)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_ALL_COMPUTER_PAGINATION
				.replace("ORDERATTRIBUTE", page.getOrderAttribute()).replace("ORDERSORT", page.getOrderSort()));

		preparedStatement.setInt(1, page.getObjetPerPage());
		preparedStatement.setInt(2, page.getObjetPerPage() * page.getPageInt());

		return preparedStatement;
	}

	public int searchAllCount() throws DAOException {

		int nbComputer = 0;
		try (Connection connexion = dataSource.getConnection();
				Statement statement = connexion.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_COUNT_ALL_COMPUTER)) {

			while (resultSet.next()) {
				nbComputer = resultSet.getInt("nbComputer");

			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);

		}

		return nbComputer;
	}

	public int searchNameCount(String name) throws DAOException {

		int nbComputer = 0;
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateSearchNameCount(connexion, name,
						SQL_SEARCH_BY_NAME_COUNT);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				nbComputer = resultSet.getInt("nbComputer");

			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);

		}

		return nbComputer;
	}

	private static PreparedStatement createPrepaStateSearchNameCount(Connection connexion, String name, String sql)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
		preparedStatement.setString(1, "%" + name + "%");
		preparedStatement.setString(2, "%" + name + "%");
		return preparedStatement;
	}

	public List<Computer> searchNamePagination(Page<Computer> page, String name) throws DAOException {
		List<Computer> computers = new ArrayList<>();
		try (Connection connexion = dataSource.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForPaginationSearchName(connexion, page, name);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				Computer computer = mapperComputer.mapFromResultSet(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

		return computers;
	}

	private static PreparedStatement createPrepaStateForPaginationSearchName(Connection connexion, Page<Computer> page,
			String name) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_SEARCH_BY_NAME_COMPA_COMPU
				.replace("ORDERATTRIBUTE", page.getOrderAttribute()).replace("ORDERSORT", page.getOrderSort()));
		preparedStatement.setString(1, "%" + name + "%");
		preparedStatement.setString(2, "%" + name + "%");
		preparedStatement.setInt(3, page.getObjetPerPage());
		preparedStatement.setInt(4, page.getObjetPerPage() * page.getPageInt());

		return preparedStatement;
	}

}
