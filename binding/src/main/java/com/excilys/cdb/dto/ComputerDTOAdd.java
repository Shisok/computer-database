package com.excilys.cdb.dto;

public class ComputerDTOAdd {

	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;

	public ComputerDTOAdd() {
	}

	public ComputerDTOAdd(String computerName, String introduced, String discontinued, String companyId) {
		super();
		this.computerName = computerName;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
	}

	public ComputerDTOAdd(ComputerDTOAddBuilder computerDTOAddBuilder) {
		this.computerName = computerDTOAddBuilder.computerName;
		this.introduced = computerDTOAddBuilder.introduced;
		this.discontinued = computerDTOAddBuilder.discontinued;
		this.companyId = computerDTOAddBuilder.companyId;
	}

	public static class ComputerDTOAddBuilder {
		private String computerName;
		private String introduced;
		private String discontinued;
		private String companyId;

		public ComputerDTOAddBuilder(String computerName) {
			this.computerName = computerName;
		}

		public ComputerDTOAddBuilder introduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOAddBuilder discontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOAddBuilder company(String companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerDTOAdd build() {
			ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd(this);

			return computerDTOAdd;
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

	@Override
	public String toString() {
		return "ComputerDTOAdd [computerName=" + computerName + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", companyId=" + companyId + "]";
	}

}
