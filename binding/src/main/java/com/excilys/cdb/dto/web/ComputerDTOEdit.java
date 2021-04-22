package com.excilys.cdb.dto.web;

public class ComputerDTOEdit {
	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;
	private String id;

	public ComputerDTOEdit() {
	}

	public ComputerDTOEdit(ComputerDTOEditBuilder computerDTOAddBuilder) {
		this.computerName = computerDTOAddBuilder.computerName;
		this.introduced = computerDTOAddBuilder.introduced;
		this.discontinued = computerDTOAddBuilder.discontinued;
		this.companyId = computerDTOAddBuilder.companyId;
		this.id = computerDTOAddBuilder.id;
	}

	public static class ComputerDTOEditBuilder {
		private String computerName;
		private String introduced;
		private String discontinued;
		private String companyId;
		private String id;

		public ComputerDTOEditBuilder(String computerName) {
			this.computerName = computerName;
		}

		public ComputerDTOEditBuilder introduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOEditBuilder discontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOEditBuilder company(String companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerDTOEditBuilder id(String id) {
			this.id = id;
			return this;
		}

		public ComputerDTOEdit build() {
			ComputerDTOEdit computerDTOList = new ComputerDTOEdit(this);

			return computerDTOList;
		}

	}

	public String getComputerName() {
		return computerName;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getId() {
		return id;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setId(String id) {
		this.id = id;
	}
}
