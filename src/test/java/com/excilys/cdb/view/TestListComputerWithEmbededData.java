package com.excilys.cdb.view;

import java.io.File;
import java.sql.Connection;

import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.cdb.dao.CompanyDAOImpl;
import com.excilys.cdb.dao.DBConnexion;
import com.excilys.cdb.dao.DataSourceDBUnitTest;

public class TestListComputerWithEmbededData extends DataSourceDBUnitTest {

	private static final String GECKO_DRIVER_PATH = "/home/excilys/Téléchargements/geckodriver-v0.29.0-linux64/geckodriver";

	private static WebDriver driver;

	@Test
	public void testSearchAllPaginationCompanyDAO() throws Exception {
		DBConnexion dbConnexionMock = Mockito.mock(DBConnexion.class);
		Connection conn = getDataSource().getConnection();
		Mockito.when(dbConnexionMock.getConnection()).thenReturn(conn);
		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();
		companyDAOImpl.setDbConnexion(dbConnexionMock);
		File file = new File(GECKO_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");
		driver.quit();
	}

}
