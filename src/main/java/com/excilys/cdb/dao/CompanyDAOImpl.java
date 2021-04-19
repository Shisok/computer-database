package com.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dto.CompanyDTOPersistance;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;

@Repository
public class CompanyDAOImpl {

	private JdbcTemplate jdbcTemplate;
	private MapperCompany mapperCompany;

	private SessionFactory sessionFactory;

	public CompanyDAOImpl(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory, MapperCompany mapperCompany) {

		this.jdbcTemplate = jdbcTemplate;

		this.sessionFactory = sessionFactory;
		this.mapperCompany = mapperCompany;
	}

	private static final int OBJECT_NUMBER_PER_PAGE = 10;

	private static final String SQL_ALL_COMPANY = "FROM CompanyDTOPersistance ORDER BY id";

	private static final String SQL_ALL_COMPANY_PAGINATION = "FROM CompanyDTOPersistance ORDER BY id ";
//	private static final String SQL_DELETE = "DELETE FROM company WHERE id=:id;";
	private static final String SQL_DELETE = "DELETE FROM CompanyDTOPersistance WHERE id=:id;";
//	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id=:id;";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM ComputerDTOPersistance WHERE company_id=:id;";

	public List<Company> searchAll() {
		List<Company> companies = new ArrayList<>();
		List<CompanyDTOPersistance> companiesDTO = new ArrayList<>();
		try {
			Session session = sessionFactory.getCurrentSession();
			companiesDTO = session.createQuery(SQL_ALL_COMPANY, CompanyDTOPersistance.class).list();
			companies = mapperCompany.mapFromListDTOPersistanceToListModel(companiesDTO);
			return companies;
		} catch (HibernateException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return companies;
	}

	public List<Company> searchAllPagination(int page) throws DAOException {
		List<Company> companies = new ArrayList<>();
		List<CompanyDTOPersistance> companiesDTO = new ArrayList<>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<CompanyDTOPersistance> query = session.createQuery(SQL_ALL_COMPANY_PAGINATION,
					CompanyDTOPersistance.class);
			query.setFirstResult(page * OBJECT_NUMBER_PER_PAGE);
			query.setMaxResults(OBJECT_NUMBER_PER_PAGE);
			companiesDTO = query.list();
			companies = mapperCompany.mapFromListDTOPersistanceToListModel(companiesDTO);
			return companies;
		} catch (HibernateException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		return companies;

	}

	public void delete(Long id) throws DAOException {

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<?> queryComputer = session.createQuery(SQL_DELETE_COMPUTER);
			queryComputer.setParameter("id", id);
			queryComputer.executeUpdate();

			Query<?> queryCompany = session.createQuery(SQL_DELETE);
			queryCompany.setParameter("id", id);
			int statut = queryCompany.executeUpdate();
//			query.setParameter("limit", OBJECT_NUMBER_PER_PAGE);
//			query.setParameter("offset", page * OBJECT_NUMBER_PER_PAGE);
			if (statut == 0) {
				throw new DAOException("Échec de la suppression de la company, aucune ligne ajoutée dans la table.");
			}
		} catch (HibernateException e) {
			LoggerCdb.logError(this.getClass(), e);
		}
		try {

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);
			jdbcTemplate.update(SQL_DELETE_COMPUTER, params);
			int statut = jdbcTemplate.update(SQL_DELETE, params);

			if (statut == 0) {
				throw new DAOException("Échec de la suppression de la company, aucune ligne ajoutée dans la table.");
			}

		} catch (DataAccessException e) {
			LoggerCdb.logError(this.getClass(), e);
		}

	}

}
