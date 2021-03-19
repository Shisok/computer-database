package com.excilys.cdb.dto;

public class ComputerDTOAdd {

	private String computerName;
	private String introduced;
	private String discontinued;
	private String companyId;

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

//		private void validateUserObject(ComputerDTOAdd computerDTOAdd) {
//			// Do some basic validations to check
//			// if user object does not break any assumption of system
//		}

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

}
