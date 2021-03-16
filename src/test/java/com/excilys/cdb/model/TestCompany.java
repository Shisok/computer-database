package com.excilys.cdb.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCompany {

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsAndHashcode() {
		Company comp1 = new Company.CompanyBuilder(1L).name("test").build();
		Company comp2 = new Company.CompanyBuilder(1L).name("test").build();
		Computer computer = new Computer.ComputerBuilder(1L).build();
		assertEquals(comp1, comp2);
		assertTrue(comp1.hashCode() == comp2.hashCode());
		assertFalse(comp1.equals(computer));
	}

}
