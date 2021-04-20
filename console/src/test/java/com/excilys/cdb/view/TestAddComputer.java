package com.excilys.cdb.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestAddComputer {

	private static final String GECKO_DRIVER_PATH = "/home/excilys/Téléchargements/geckodriver-v0.29.0-linux64/geckodriver";

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
		driver.get(" http://localhost:8080/computer-database-db/AddComputer");
		WebElement applicatoinTitleElement = driver.findElement(By.id("applicationTitle"));
		assertEquals(applicatoinTitleElement.getText(), "Application - Computer Database");
		WebElement pageTitleElement = driver.findElement(By.id("addTitle"));
		assertTrue(pageTitleElement.getText().equals("Add Computer"));
	}

	@Test
	public void testAddComputer() throws InterruptedException {
		driver.get(" http://localhost:8080/computer-database-db/AddComputer");
		WebElement nameElement = driver.findElement(By.id("computerName"));
		nameElement.sendKeys("test");
		WebElement introducedElement = driver.findElement(By.id("introduced"));
		introducedElement.sendKeys("2020-02-20");
		WebElement discontinuedElement = driver.findElement(By.id("discontinued"));
		discontinuedElement.sendKeys("2020-02-21");
		WebElement companyIdElement = driver.findElement(By.id("companyId"));
		Select selectObject = new Select(companyIdElement);
		selectObject.selectByVisibleText("IBM");
		Thread.sleep(3000);
	}
}
