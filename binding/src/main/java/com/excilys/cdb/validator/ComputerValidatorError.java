package com.excilys.cdb.validator;

public enum ComputerValidatorError {
	NONAME("The computer doesn't have a name."), INTROBEFOREDISCON("Discontinued is before introduced."),
	NOINTRO("Discontinued cannot be registered without introduced");

	private String message;

	ComputerValidatorError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
