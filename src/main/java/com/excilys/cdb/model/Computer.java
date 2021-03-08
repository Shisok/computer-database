package com.excilys.cdb.model;

import java.sql.Date;

public class Computer {
	int id;
	String name;
	Date introduced;
	Date discontinued;
	int company_id;

	/**
	 * @param id           any int value
	 * @param name         any String value
	 * @param introduced   any Date Value
	 * @param discontinued any Date Value
	 * @param company_id   int Value of the company
	 */
	public Computer(int id, String name, Date introduced, Date discontinued, int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}

	public Computer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Computer(int id, String name, Date introduced, int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.company_id = company_id;
	}

	public Computer(int id, String name, int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.company_id = company_id;
	}

	public Computer(int id, String name, Date introduced) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
	}

	/**
	 * Default Constructor
	 */
	public Computer() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
