package test.com.excilys.cdb.dao;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.dao.ComputerDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.model.Computer;

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

		computerDAOImpl = new ComputerDAOImpl(daoFactory);
	}

	@After
	public void tearDown() {
		computerDAOImpl = null;
	}

	@Test
	public void testAllRequete() {
		Computer computer = new Computer.ComputerBuilder(null).name("testname").introduced(LocalDate.now()).build();
		computerDAOImpl.create(computer);
		List<Computer> listComp = computerDAOImpl.searchAll();
		System.out.println("All computer + added");
		System.out.println("taille liste comp :" + listComp.size());
		for (Computer comp : listComp) {
			System.out.println(comp.toString());
		}
		Optional<Computer> computerCreatedSearched = computerDAOImpl.search(computer.getId());
		// sachant qu'il existe dans la base de données
		Optional<Computer> computerSearched = computerDAOImpl.search(574L);

		System.out.println(computerSearched.toString());

		computerDAOImpl.delete(computer.getId());
		System.out.println("All computer + deleted");
		List<Computer> listCompu = computerDAOImpl.searchAll();
		for (Computer comp : listCompu) {
			System.out.println(comp.toString());
		}
		Optional<Computer> computerDeletedSearch = computerDAOImpl.search(computer.getId());
		assertTrue(computer.equals(computerCreatedSearched.get()));
		assertTrue(computerSearched.get() != null);
		assertTrue(computerDeletedSearch.get() == null);

	}
}
