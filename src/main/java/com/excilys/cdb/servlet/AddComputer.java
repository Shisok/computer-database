package com.excilys.cdb.servlet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.exception.ValidatorException;
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.validator.ComputerValidator;
import com.excilys.cdb.validator.ComputerValidatorError;

@Controller
public class AddComputer {

	@Autowired
	private MapperCompany mapperCompany;
	@Autowired
	private MapperComputer mapperComputer;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerValidator computerValidator;

	@GetMapping(value = "/AddComputer")
	protected ModelAndView showAddComputer(@ModelAttribute("computerAdded") String computerAdded,
			@ModelAttribute("erreurName") String erreurName, @ModelAttribute("erreurNoIntro") String erreurNoIntro,
			@ModelAttribute("erreurDiscoBeforeIntro") String erreurDiscoBeforeIntro) {
		ModelAndView modelAndView = new ModelAndView("addComputer", "ComputerDTOAdd", new ComputerDTOAdd());
		List<Company> listCompanies = this.companyService.searchAllCompany();
		List<CompanyDTO> listCompaniesDTO = listCompanies.stream().map(c -> mapperCompany.mapFromModelToDTO(c))
				.collect(Collectors.toList());
		modelAndView.addObject("computerAdded", computerAdded);
		modelAndView.addObject("erreurName", erreurName);
		modelAndView.addObject("erreurNoIntro", erreurNoIntro);
		modelAndView.addObject("erreurDiscoBeforeIntro", erreurDiscoBeforeIntro);
		modelAndView.addObject("listCompanies", listCompaniesDTO);

		return modelAndView;
	}

	@PostMapping("/AddComputer")
	protected RedirectView addComputer(@ModelAttribute("ComputerDTOAdd") ComputerDTOAdd computerDTOAdd,
			RedirectAttributes redir) {

		try {
//			handleBindingError(result, modelAndView);
			computerValidator.validationComputerDTOAdd(computerDTOAdd);
			Computer computer = mapperComputer.mapFromDTOAddToModel(computerDTOAdd);
			computerService.createComputer(computer);
			redir.addFlashAttribute("computerAdded", "The computer was successfully added");
			return new RedirectView("/AddComputer", true);
		} catch (ValidatorException e) {
			LoggerCdb.logError(getClass(), e);
			showError(redir, e);
			return new RedirectView("/AddComputer", true);
		}

	}

//	private void handleBindingError(BindingResult result, ModelAndView modelAndView) {
//		if (result.hasErrors()) {
//			result.getAllErrors().stream().forEach(e -> {
//				String name = e.getObjectName();
//				if (name.equals("name")) {
//					LoggerCdb.logDebug(getClass(), "Binding error with name");
//					modelAndView.addObject("erreurName", "Binding error with name");
//					throw new ValidatorException("Error with name");
//				} else if (name.equals("introduced")) {
//					LoggerCdb.logDebug(getClass(), "Binding error with introduced");
//					modelAndView.addObject("erreurName", "Binding error with introduced");
//					throw new ValidatorException("Error with introduced");
//				} else if (name.equals("discontinued")) {
//					LoggerCdb.logDebug(getClass(), "Binding error with discontinued");
//					modelAndView.addObject("erreurName", "Binding error with discontinued");
//					throw new ValidatorException("Error with discontinued");
//				} else if (name.equals("companyId")) {
//					LoggerCdb.logDebug(getClass(), "Binding error with companyId");
//					modelAndView.addObject("erreurName", "Binding error with companyId");
//					throw new ValidatorException("Error with companyId");
//				} else {
//					LoggerCdb.logDebug(getClass(), e.getObjectName());
//					modelAndView.addObject("erreurName", "Error occured");
//					throw new ValidatorException("Error occured");
//				}
//			});
//		}
//	}

	private void showError(RedirectAttributes redir, ValidatorException e) {
		if (e.getMessage().equals(ComputerValidatorError.NONAME.getMessage())) {
			redir.addFlashAttribute("erreurName", e.getMessage());
		} else if (e.getMessage().equals(ComputerValidatorError.INTROBEFOREDISCON.getMessage())) {
			redir.addFlashAttribute("erreurDiscoBeforeIntro", e.getMessage());
		} else if (e.getMessage().equals(ComputerValidatorError.NOINTRO.getMessage())) {
			redir.addFlashAttribute("erreurNoIntro", e.getMessage());

		}
	}

}
