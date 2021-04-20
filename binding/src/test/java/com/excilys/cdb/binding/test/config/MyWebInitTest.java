package com.excilys.cdb.binding.test.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.excilys.cdb.binding.config" })
public class MyWebInitTest implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		context.register(BindingConfig.class);
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

}