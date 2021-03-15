package com.excilys.cdb.model;

/**
 * @author hhu
 *
 */

public class Company {

	Long id;
	String name;

	private Company(CompanyBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
