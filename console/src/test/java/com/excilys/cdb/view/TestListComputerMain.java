package com.excilys.cdb.view;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestListComputerMain {

	private static final String GECKO_DRIVER_PATH = "/home/excilys/Téléchargements/geckodriver-v0.29.0-linux64/geckodriver";
	// private static final String PHANTOMJS_DRIVER_PATH =
	// "/home/excilys/phantomjs-2.1.1-linux-x86_64/bin/phantomjs";

	public static void main(String[] args) {

		// File file = new File(PHANTOMJS_DRIVER_PATH);
//		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
//		PhantomJSDriver driver = new PhantomJSDriver();

		File file = new File(GECKO_DRIVER_PATH);
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		WebDriver driver = new FirefoxDriver();
		driver.get(" http://localhost:8080/computer-database-db/ListComputer");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
	}
}