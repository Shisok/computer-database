package com.excilys.cdb.test;

import java.time.LocalDate;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class testDao {
	public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl(daoFactory);
		Company company = companyDAOImpl.trouver("Apple Inc.");
		System.out.println(company.getName());
		Computer computer = new Computer.ComputerBuilder(null).name("testname").introduced(LocalDate.now()).build();
		System.out.println(computer.getName());
		System.out.println(computer.getIntroduced());
		System.out.println(computer.getId());
	}
}
