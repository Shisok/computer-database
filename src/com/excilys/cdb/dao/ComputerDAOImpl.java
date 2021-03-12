package com.excilys.cdb.dao;

import static com.excilys.cdb.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.excilys.cdb.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Computer;

public class ComputerDAOImpl {
	private DBConnexion daoFactory;

	public ComputerDAOImpl(DBConnexion daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT = "INSERT INTO computer ( name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id=?;";
	private static final String SQL_SELECT = "SELECT * FROM computer WHERE id = ?;";
	private static final String SQL_ALL_COMPUTER = "SELECT id,name,introduced,discontinued,company_id From computer;";
	private static final String SQL_ALL_COMPUTER_PAGINATION = "SELECT id,name,introduced,discontinued,company_id From computer ORDER BY id LIMIT ?,?;";

	private static Computer map(ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		if (resultSet.getDate("introduced") != null) {
			computer.setIntroduced(resultSet.getDate("introduced").toLocalDate());
		}
		if (resultSet.getDate("discontinued") != null) {
			computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
		}
		if (resultSet.getObject("company_id") == null) {
			computer.setCompanyId(null);
		} else {
			computer.setCompanyId(resultSet.getLong("company_id"));
		}
		return computer;
	}

	public void create(Computer computer) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, computer.getName(),
					computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId());

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
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	public void update(Computer computer) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, computer.getName(),
					computer.getIntroduced(), computer.getDiscontinued(), computer.getCompanyId(), computer.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException("Échec de la mise à jour de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	public void delete(Computer computer) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE, false, computer.getId());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException("Échec de la suppression de l'ordinateur, aucune ligne ajoutée dans la table.");
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	public Computer search(Long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT, false, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				computer = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return computer;
	}

	public List<Computer> searchAll() throws DAOException {
		List<Computer> computers = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_COMPUTER, false);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				computer = map(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return computers;
	}

	public List<Computer> searchAllPagination(int page) throws DAOException {
		List<Computer> computers = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		int offset = page * 10;
		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_ALL_COMPUTER_PAGINATION, false, offset,
					10);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				computer = map(resultSet);
				computers.add(computer);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return computers;
	}

}
