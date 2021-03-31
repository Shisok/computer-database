package com.excilys.cdb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({ "com.excilys.cdb.service", "com.excilys.cdb.controller", "com.excilys.cdb.dao",
		"com.excilys.cdb.mapper", "com.excilys.cdb.model", "com.excilys.cdb.validator", "com.excilys.cdb.logger",
		"com.excilys.cdb.view" })
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(MyWebConfig.class);
		context.setServletContext(container);

	}

	@Bean
	public HikariDataSource getDataSource() {
		return new HikariDataSource(new HikariConfig("/com/excilys/cdb/dao/datasource.properties"));
	}
}