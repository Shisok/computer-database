package com.excilys.cdb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.MyWebAppInitializer;
import com.excilys.cdb.view.CliMenu;

public class Cdb {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(MyWebAppInitializer.class);
		CliMenu cliMenu = context.getBean(CliMenu.class);
		cliMenu.mainMenu();
		((ConfigurableApplicationContext) context).close();
	}

}
