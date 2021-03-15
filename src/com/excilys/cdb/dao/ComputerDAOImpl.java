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

import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

public class ComputerDAOImpl {
	private DBConnexion dbConnexion;
	private MapperComputer mapperComputer;

	private static final String SQL_INSERT = "INSERT INTO computer ( name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?;";
	private static final String SQL_SELECT = "SELECT * FROM computer WHERE id = ?;";
	private static final String SQL_ALL_COMPUTER = "SELECT id,name,introduced,discontinued,company_id From computer;";
	private static final String SQL_ALL_COMPUTER_PAGINATION = "SELECT id,name,introduced,discontinued,company_id From computer ORDER BY id LIMIT ?,?;";
	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	public ComputerDAOImpl(DBConnexion dbConnexion) {
		this.dbConnexion = dbConnexion;
		this.mapperComputer = new MapperComputer();
	}

	public void create(Computer computer) throws DAOException {

		ResultSet valeursAutoGenerees = null;

		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForCreate(connexion, computer);) {

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
			System.out.println("An error occured because date cannot be bellow 1970-01-01.");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("An error occured because the company doesn't exist.");
		} catch (SQLException e) {
			System.out.println("An has error occured during the creation.");
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

		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForUpdate(connexion, computer);) {

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la mise à jour de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
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

		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_DELETE);) {

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			System.out.println("An error occured while deleting the computer");
		}

	}

	public Optional<Computer> search(Long id) throws DAOException {
		Optional<Computer> computer = Optional.empty();
		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateWithCompId(connexion, id, SQL_SELECT);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			if (resultSet.next()) {
				computer = Optional.ofNullable(mapperComputer.mapFromResultSet(resultSet));
			}
		} catch (SQLException e) {
			System.out.println("An error occured during the research.");
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

		try (Connection connexion = dbConnexion.getConnection();
				Statement statement = connexion.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL_ALL_COMPUTER)) {

			while (resultSet.next()) {

				Computer computer = mapperComputer.mapFromResultSet(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			System.out.println("An error occured during the research.");
		}

		return computers;
	}

	public List<Computer> searchAllPagination(int page) throws DAOException {
		List<Computer> computers = new ArrayList<>();

		int offset = page * 10;
		try (Connection connexion = dbConnexion.getConnection();
				PreparedStatement preparedStatement = createPrepaStateForPagination(connexion, offset);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				Computer computer = mapperComputer.mapFromResultSet(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			System.out.println("An error occured while searching the page.");
		}

		return computers;
	}

	private static PreparedStatement createPrepaStateForPagination(Connection connexion, int offset)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(SQL_ALL_COMPUTER_PAGINATION,
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, offset);
		preparedStatement.setInt(2, OBJECT_NUMBER_PER_PAGE);
		return preparedStatement;
	}

}
