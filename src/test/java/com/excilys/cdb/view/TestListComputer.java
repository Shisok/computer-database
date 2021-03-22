package com.excilys.cdb.view;

import java.io.File;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class TestListComputer {

	public static void main(String[] args) {

		File file = new File("/home/excilys/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
		System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

		PhantomJSDriver driver = new PhantomJSDriver();

		driver.get(" http://www.all4test.fr ");

		System.out.println(" Successfully opened the website www.Store.Demoqa.com ");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.quit();
	}
}