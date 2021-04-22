package com.excilys.cdb.dto.rest;

import java.io.Serializable;

public class CompanyDTORest implements Serializable {

	private static final long serialVersionUID = -3138220090868480968L;

	Long id;
	String name;

	private CompanyDTORest(CompanyDTORestBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;

	}

	public static class CompanyDTORestBuilder {

		Long id;
		String name;

		public CompanyDTORestBuilder(Long id) {
			this.id = id;
		}

		public CompanyDTORestBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CompanyDTORest build() {
			CompanyDTORest company = new CompanyDTORest(this);
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
		CompanyDTORest other = (CompanyDTORest) obj;
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
