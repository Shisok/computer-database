package com.excilys.cdb.servlet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import javax.servlet.ServletContext;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.excilys.cdb.config.MyWebConfig;
import com.excilys.cdb.dao.DataSourceDBUnitTest;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { MyWebConfig.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@DatabaseSetup("/com/excilys/cdb/dao/data.xml")
public class TestListComputerControllerMVCWithDbUnit extends DataSourceDBUnitTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	/**
	 * Test mock servlet context injection.
	 */
	@Test
	public void testProvideBean() {
		ServletContext servletContext = webApplicationContext.getServletContext();
		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(webApplicationContext.getBean("computerValidatorAdd"));
	}

	@Test
	public void testReturnViewName() throws Exception {
		this.mockMvc.perform(get("/ListComputer")).andDo(print()).andExpect(view().name("dashboard"));
	}

	@Test
	public void testGetModelDefault() throws Exception {
		this.mockMvc.perform(get("/ListComputer")).andExpect(status().isOk())
				.andExpect(model().attribute("numeroPage", 1)).andExpect(model().attribute("indexDebut", 1))
				.andExpect(model().attribute("indexFin", 2)).andExpect(model().attribute("lang", "en"))
				.andExpect(model().attribute("countComputer", 13)).andExpect(model().attribute("pageMax", 2))
				.andExpect(model().attributeExists("listeComputers"))
				.andExpect(model().attribute("listeComputers", hasSize(10)))
				.andExpect(model().attributeDoesNotExist("search"));
	}

//	@Test
//	public void testPost() throws Exception {
//		this.mockMvc.perform(post("/ListComputer")).andExpect(status().isOk())
//				.andExpect(model().attribute("numeroPage", 1)).andExpect(model().attribute("indexDebut", 1))
//				.andExpect(model().attribute("indexFin", 2)).andExpect(model().attribute("lang", "en"))
//				.andExpect(model().attribute("countComputer", 13)).andExpect(model().attribute("pageMax", 2))
//				.andExpect(model().attributeExists("listeComputers"))
//				.andExpect(model().attributeDoesNotExist("search"));
//	}
}
