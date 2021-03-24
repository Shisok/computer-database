package com.excilys.cdb.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestListComputer {

	private static final String GECKO_DRIVER_PATH = "/home/excilys/Téléchargements/geckodriver-v0.29.0-linux64/geckodriver";
	private static final String DASHBOARD_HOMETITLE_REGEX = "([0-9]*)+\\s+Computers found";
	private static WebDriver driver;

	@BeforeClass
	public static void init() {

		File file = new File(GECKO_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void finish() {
		driver.quit();
	}

	@Test
	public void testOuverture() {
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");

		WebElement applicatoinTitleElement = driver.findElement(By.id("applicationTitle"));
		assertEquals(applicatoinTitleElement.getText(), "Application - Computer Database");
		WebElement pageTitleElement = driver.findElement(By.id("homeTitle"));
		assertTrue(pageTitleElement.getText().matches(DASHBOARD_HOMETITLE_REGEX));

	}

	@Test
	public void testPagination() {
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");
		WebElement page2Element = driver.findElement(By.id("page2"));
		page2Element.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=2", driver.getCurrentUrl());
		WebElement page5Element = driver.findElement(By.id("page5"));
		page5Element.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=5", driver.getCurrentUrl());
		WebElement page7Element = driver.findElement(By.id("page7"));
		page7Element.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=7", driver.getCurrentUrl());
		WebElement previousPageElement = driver.findElement(By.id("previousPage"));
		previousPageElement.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=6", driver.getCurrentUrl());
		WebElement nextPageElement = driver.findElement(By.id("nextPage"));
		nextPageElement.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=7", driver.getCurrentUrl());
		WebElement firstPageElement = driver.findElement(By.id("firstPage"));
		firstPageElement.click();
		assertEquals("http://localhost:8080/computer-database-db/ListComputer?pageno=1", driver.getCurrentUrl());
		try {
			WebElement page10Element = driver.findElement(By.id("page10"));
			page10Element.click();
		} catch (NoSuchElementException e) {
			assert (e.getMessage().contains("Unable to locate element: #page10"));
		}
	}

	@Test
	public void testNbObject() {
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");
		WebElement nbObject100Element = driver.findElement(By.id("nbObject100"));
		nbObject100Element.click();
		WebElement nbObject10Element = driver.findElement(By.id("nbObject10"));
		nbObject10Element.click();
		WebElement nbObject50Element = driver.findElement(By.id("nbObject50"));
		nbObject50Element.click();

	}

	@Test
	public void testNaviguerVersAdd() {
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");
		WebElement addComputerElement = driver.findElement(By.id("addComputer"));
		addComputerElement.click();
		assertEquals("http://localhost:8080/computer-database-db/AddComputer", driver.getCurrentUrl());

	}
}
