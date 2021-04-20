package com.excilys.cdb.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestPage {
	@Test
	public void testEqualsAndHashcode() {
		Page<Computer> page = new Page<Computer>();
		ArrayList<Computer> listOfComputer = new ArrayList<Computer>();
		assertEquals(page.getPageInitial(), 0);
		assertTrue(page.getPageInt() == 0);
		assertEquals(page.getContentPage(), listOfComputer);
		page.setPageInitial(1);
		page.setPageInt(2);
		listOfComputer.add(new Computer.ComputerBuilder(null).name("test").build());
		page.setContentPage(listOfComputer);
		page.setObjetPerPage(10);
		page.setNbComputer(1);
		page.setPageMax();
		page.setIndex();
		assertEquals(page.getPageInitial(), 1);
		assertTrue(page.getPageInt() == 2);
		assertEquals(page.getContentPage(), listOfComputer);
		assertEquals(page.getObjetPerPage(), 10);
		assertEquals(page.getIndexDebut(), 1);
		assertEquals(page.getIndexFin(), 1);

	}
}
