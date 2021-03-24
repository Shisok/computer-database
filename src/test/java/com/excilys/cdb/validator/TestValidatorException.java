package com.excilys.cdb.validator;

import java.time.LocalDate;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.excilys.cdb.exception.ValidatorException;
import com.excilys.cdb.model.Computer;

public class TestValidatorException {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testValidatorComputerNameNull() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		Computer computer = new Computer.ComputerBuilder(null).name(null).build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);

	}

	@Test
	public void testValidatorComputerNameEmpty() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		Computer computer = new Computer.ComputerBuilder(null).name("").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);

	}

	@Test
	public void testValidatorComputerNameWithBlank() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		Computer computer = new Computer.ComputerBuilder(null).name("    ").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);
	}

	@Test
	public void testValidatorComputerDiscondWithoutIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NOINTRO.getMessage()));
		Computer computer = new Computer.ComputerBuilder(null).name("test").discontinued(LocalDate.parse("2020-02-20"))
				.build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);
	}

	@Test
	public void testValidatorComputerDiscondBeforeIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.INTROBEFOREDISCON.getMessage()));
		Computer computer = new Computer.ComputerBuilder(null).name("test").introduced(LocalDate.parse("2020-04-20"))
				.discontinued(LocalDate.parse("2020-02-20")).build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);
	}

	@Test
	public void testValidatorComputerCorrect() {
		Computer computer = new Computer.ComputerBuilder(null).name("test").introduced(LocalDate.parse("2020-01-20"))
				.discontinued(LocalDate.parse("2020-02-20")).build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputer(computer);
	}
}
