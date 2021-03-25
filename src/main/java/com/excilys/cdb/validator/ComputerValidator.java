package com.excilys.cdb.validator;

import java.time.LocalDate;

import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.dto.ComputerDTOEdit;
import com.excilys.cdb.exception.ValidatorException;

public class ComputerValidator {

	// TODO pas pour les objets metier
	private ComputerValidator() {
	}

	private static ComputerValidator INSTANCE = null;

	public static ComputerValidator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ComputerValidator();
		}
		return INSTANCE;
	}

	public void validationComputerDTOAdd(ComputerDTOAdd computerDTOAdd) throws ValidatorException {

		String name = computerDTOAdd.getComputerName();
		String introduced = computerDTOAdd.getIntroduced();
		String discontinued = computerDTOAdd.getDiscontinued();

		validateName(name);

		validateDiscontinued(introduced, discontinued);

	}

	public void validationComputerDTOEdit(ComputerDTOEdit computerDTOEdit) throws ValidatorException {

		String name = computerDTOEdit.getComputerName();
		String introduced = computerDTOEdit.getIntroduced();
		String discontinued = computerDTOEdit.getDiscontinued();

		validateName(name);

		validateDiscontinued(introduced, discontinued);

	}

	private void validateDiscontinued(String introduced, String discontinued) {
		if (discontinued != null && !discontinued.isEmpty()) {
			LocalDate discontinuedLD = LocalDate.parse(discontinued);

			if (introduced != null && !introduced.isEmpty()) {

				LocalDate introducedLD = LocalDate.parse(introduced);
				if (introducedLD.isAfter(discontinuedLD) || introducedLD.equals(discontinuedLD)) {
					throw new ValidatorException(ComputerValidatorError.INTROBEFOREDISCON.getMessage());
				}
			} else {
				throw new ValidatorException(ComputerValidatorError.NOINTRO.getMessage());
			}
		}
	}

	private void validateName(String name) {
		if ("".equals(name) || null == name || name.matches("\\s*")) {
			throw new ValidatorException(ComputerValidatorError.NONAME.getMessage());
		}
	}

}
