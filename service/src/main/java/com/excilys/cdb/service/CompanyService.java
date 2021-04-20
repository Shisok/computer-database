package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.exception.DAOConfigurationException;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.model.Company;

@Service
public class CompanyService {

	CompanyDAOImpl companyDAOImpl;

	public CompanyService(CompanyDAOImpl companyDAOImpl) {

		this.companyDAOImpl = companyDAOImpl;
	}

	@Transactional
	public List<Company> searchAllCompany() {
		try {

			return companyDAOImpl.searchAll();

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return new ArrayList<Company>();

	}

	@Transactional
	public boolean deleteCompany(Long compToDeleteID) {
		boolean success = false;
		try {

			companyDAOImpl.delete(compToDeleteID);
			success = true;

		} catch (DAOException e) {
			LoggerCdb.logError(getClass(), e);
		} catch (DAOConfigurationException e) {
			LoggerCdb.logError(getClass(), e);
		}
		return success;
	}
}
