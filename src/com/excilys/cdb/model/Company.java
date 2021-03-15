package com.excilys.cdb.model;

/**
 * @author excilys
 *
 */

public class Company {

	Long id;
	String name;

	private Company(CompanyBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;

	}

	/**
	 * default Constructor.
	 */
	public Company() {
		super();

	}

	public static class CompanyBuilder {

		Long id;
		String name;

		public CompanyBuilder(Long id) {
			this.id = id;
		}

		public CompanyBuilder name(String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			Company company = new Company(this);
			return company;
		}

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
