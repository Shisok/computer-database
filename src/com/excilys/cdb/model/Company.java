package com.excilys.cdb.model;

/**
 * @author excilys
 *
 */

public class Company {

	Long id;
	String name;

	/**
	 * @param id   any Long Value
	 * @param name any String Value
	 */
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * default Constructor.
	 */
	public Company() {
		super();

	}

	/**
	 * @return any id Value
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id any Long value
	 */
	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

}
