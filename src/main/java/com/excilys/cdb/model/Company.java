package com.excilys.cdb.model;

/**
 * @author excilys
 *
 */

public class Company {

	int id;
	String name;

	/**
	 * @param id   any int Value
	 * @param name any String Value
	 */
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * default Constructor
	 */
	public Company() {
		super();

	}

	/**
	 * @return any id Value
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id any int value
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return any String value
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name any String Value
	 */
	public void setName(String name) {
		this.name = name;
	}

}
