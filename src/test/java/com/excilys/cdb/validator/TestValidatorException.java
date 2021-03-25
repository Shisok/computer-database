package com.excilys.cdb.validator;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.exception.ValidatorException;

public class TestValidatorException {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testValidatorComputerNameNull() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder(null).build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);

	}

	@Test
	public void testValidatorComputerNameEmpty() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);

	}

	@Test
	public void testValidatorComputerNameWithBlank() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("    ").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerDiscondWithoutIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NOINTRO.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").discontinued("2020-02-20")
				.build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerDiscondBeforeIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.INTROBEFOREDISCON.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").introduced("2020-04-20")
				.discontinued("2020-02-20").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerCorrect() {
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").introduced("2020-01-20")
				.discontinued("2020-02-20").build();
		ComputerValidator computerValidator = ComputerValidator.getInstance();
		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}
}
