package com.excilys.cdb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.MyWebApplicationInitializerCli;
import com.excilys.cdb.view.CliMenu;

public class Cdb {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(MyWebApplicationInitializerCli.class);
		CliMenu cliMenu = context.getBean(CliMenu.class);
		cliMenu.mainMenu();
	}

}
