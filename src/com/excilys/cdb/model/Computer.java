package com.excilys.cdb.model;

import java.time.LocalDate;

public class Computer {
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Long company_id;

	/**
	 * @param id           any Long value
	 * @param name         any String value
	 * @param introduced   any Date Value
	 * @param discontinued any Date Value
	 * @param company_id   Long Value of the company
	 */
	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}

	private Computer(ComputerBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.company_id = computerBuilder.company_id;
	}

//	public Computer(Long id, String name) {
//		super();
//		this.id = id;
//		this.name = name;
//	}
//
//	public Computer(Long id, String name, Date introduced, Long company_id) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.introduced = introduced;
//		this.company_id = company_id;
//	}
//
//	public Computer(Long id, String name, Long company_id) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.company_id = company_id;
//	}
//
//	public Computer(Long id, String name, Date introduced) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.introduced = introduced;
//	}

	/**
	 * Default Constructor
	 */
	public Computer() {
		super();
	}

	public static class ComputerBuilder {
		private Long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Long company_id;

		public ComputerBuilder(Long id) {
			this.id = id;
		}

		public ComputerBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder introduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder discontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerBuilder company_id(Long company_id) {
			this.company_id = company_id;
			return this;
		}

		// Return the finally consrcuted User object
		public Computer build() {
			Computer computer = new Computer(this);
			// validateComputerObject(user);
			return computer;
		}

//		private void validateComputerObject(Computer computer) {
//			// Do some basic validations to check
//			// if user object does not break any assumption of system
//		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
