package com.excilys.cdb.config;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.excilys.cdb.service", "com.excilys.cdb.controller", "com.excilys.cdb.dao",
		"com.excilys.cdb.mapper", "com.excilys.cdb.model", "com.excilys.cdb.validator", "com.excilys.cdb.view",
		"com.excilys.cdb.persistance.config" })
public class MyWebApplicationInitializerCli implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(MyWebConfig.class);
		context.setServletContext(container);
		context.close();
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//		rootContext.register(MyWebConfig.class);
//		container.addListener(new ContextLoaderListener(rootContext));
//		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
//		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
//				new DispatcherServlet(dispatcherServlet));
//		dispatcher.setLoadOnStartup(1);
//		dispatcher.addMapping("/");
	}

	@Bean
	public HikariDataSource getDataSource() {
		return new HikariDataSource(new HikariConfig("/com/excilys/cdb/dao/datasource.properties"));
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(HikariDataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(HikariDataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

//	@Bean
//	public PlatformTransactionManager txManager() {
//		return new DataSourceTransactionManager(getDataSource());
//
//	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setPackagesToScan("com.excilys.cdb.dto");
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		factoryBean.setHibernateProperties(hibernateProperties);
		factoryBean.setHibernateProperties(
				(Properties) new Properties().setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}

}