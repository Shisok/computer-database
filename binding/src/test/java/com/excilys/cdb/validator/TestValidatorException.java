package com.excilys.cdb.validator;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.excilys.cdb.binding.test.config.MyWebInitTest;
import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.exception.ValidatorException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = { MyWebInitTest.class })
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class TestValidatorException {

	@Autowired
	private ComputerValidator computerValidator;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testValidatorComputerNameNull() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder(null).build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);

	}

	@Test
	public void testValidatorComputerNameEmpty() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("").build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);

	}

	@Test
	public void testValidatorComputerNameWithBlank() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NONAME.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("    ").build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerDiscondWithoutIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.NOINTRO.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").discontinued("2020-02-20")
				.build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerDiscondBeforeIntro() {
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(CoreMatchers.equalTo(ComputerValidatorError.INTROBEFOREDISCON.getMessage()));
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").introduced("2020-04-20")
				.discontinued("2020-02-20").build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}

	@Test
	public void testValidatorComputerCorrect() {
		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder("test").introduced("2020-01-20")
				.discontinued("2020-02-20").build();

		computerValidator.validationComputerDTOAdd(computerDTOAdd);
	}
}
