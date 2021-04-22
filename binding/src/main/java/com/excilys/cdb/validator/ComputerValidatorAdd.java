package com.excilys.cdb.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.cdb.dto.web.ComputerDTOAdd;

@Component
public class ComputerValidatorAdd implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ComputerDTOAdd.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ComputerDTOAdd computerDTOAdd = (ComputerDTOAdd) target;
		String name = computerDTOAdd.getComputerName();
		String introduced = computerDTOAdd.getIntroduced();
		String discontinued = computerDTOAdd.getDiscontinued();

		validateName(name, errors);

		validateDiscontinued(introduced, discontinued, errors);

	}

	private void validateDiscontinued(String introduced, String discontinued, Errors errors) {
		if (discontinued != null && !discontinued.isEmpty()) {
			LocalDate discontinuedLD = LocalDate.parse(discontinued);

			if (introduced != null && !introduced.isEmpty()) {

				LocalDate introducedLD = LocalDate.parse(introduced);

				if (introducedLD.isAfter(discontinuedLD) || introducedLD.equals(discontinuedLD)) {

//					errors.rejectValue("discontinued", "computerDTOAdd.discontinued.disconBeforeIntro");
					errors.rejectValue("discontinued", "computerDTOAdd.discontinued.disconBeforeIntro",
							new Object[] { introduced, discontinued }, "Discontinued is before Introduced.");
				}
			} else {

				errors.rejectValue("discontinued", "computerDTOAdd.discontinued.noIntro", new Object[] { discontinued },
						"Introduced is needed to input Discontinued");
			}
		}
	}

	private void validateName(String name, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "computerName", "computerDTOAdd.computerName.empty");
	}
}
