package com.excilys.cdb.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class TestDaoComputer {

	static DBConnexion daoFactory;
	ComputerDAOImpl computerDAOImpl;

	@BeforeClass
	public static void setUpFactory() {
		// DAOFactory ne doit etre instancier qu'une seule foi car Factory

		daoFactory = DBConnexion.getInstance();
	}

	@Before
	public void setUpComputerDao() {
		// DAOFactory ne doit etre instancier qu'une seule foi car Factory
		computerDAOImpl = new ComputerDAOImpl();
	}

	@After
	public void tearDown() {
		computerDAOImpl = null;
	}

//	@Test
//	public void testAllRequete() {
//		Computer computer = new Computer.ComputerBuilder(null).name("testname").introduced(LocalDate.now())
//				.company(new Company.CompanyBuilder(1L).build()).build();
//		computerDAOImpl.create(computer);
//		List<Computer> listComp = computerDAOImpl.searchAll();
//		System.out.println("All computer + added");
//		System.out.println("taille liste comp :" + listComp.size());
////		for (Computer comp : listComp) {
////			System.out.println(comp.toString());
////		}
//		Optional<Computer> computerCreatedSearched = computerDAOImpl.search(computer.getId());
//		// sachant qu'il existe dans la base de données
//		Optional<Computer> computerSearched = computerDAOImpl.search(574L);
//		System.out.println(computerSearched.toString());
//		computerDAOImpl.delete(computer.getId());
//		System.out.println("All computer + deleted");
//		List<Computer> listCompu = computerDAOImpl.searchAll();
////		for (Computer comp : listCompu) {
////			System.out.println(comp.toString());
////		}
//		Optional<Computer> computerDeletedSearch = computerDAOImpl.search(computer.getId());
//		assertTrue(computer.getId().equals(computerCreatedSearched.orElseThrow(null).getId()));
//		assertTrue(computerSearched != null);
//		assertTrue(!computerDeletedSearch.isPresent());
//	}
}