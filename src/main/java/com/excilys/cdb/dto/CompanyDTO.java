package com.excilys.cdb.dto;

public class CompanyDTO {
	String id;
	String name;

	private CompanyDTO(CompanyDTOBuilder computerDTOBuilder) {
		this.id = computerDTOBuilder.id;
		this.name = computerDTOBuilder.name;

	}

	public static class CompanyDTOBuilder {

		String id;
		String name;

		public CompanyDTOBuilder(String id) {
			this.id = id;
		}

		public CompanyDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CompanyDTO build() {
			CompanyDTO companyDTO = new CompanyDTO(this);
			return companyDTO;
		}

	}

	/**
	 * @return any id Value
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return any String value
	 */
	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
