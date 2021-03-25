package com.excilys.cdb.view;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.cdb.dao.DataSourceDBUnitTest;

public class TestListComputerWithEmbededData extends DataSourceDBUnitTest {

	private static final String GECKO_DRIVER_PATH = "/home/excilys/Téléchargements/geckodriver-v0.29.0-linux64/geckodriver";

	private static WebDriver driver;

	@Test
	public void testSearchAllPaginationCompanyDAO() throws Exception {
//
//		Connection conn = getDataSource().getConnection();
//
//		CompanyDAOImpl companyDAOImpl = new CompanyDAOImpl();

		File file = new File(GECKO_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");
		Thread.sleep(3000);
		driver.quit();
	}

}
