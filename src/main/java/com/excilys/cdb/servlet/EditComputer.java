package com.excilys.cdb.servlet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTOEdit;
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

/**
 * Servlet implementation class EditComputer.
 */
@Controller
public class EditComputer {

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

	@GetMapping(value = "/EditComputer")
	protected ModelAndView showEditComputer(@RequestParam(required = false) String id,
			@RequestParam(required = false) String name, @ModelAttribute("computerEdited") String computerEdited,
			@ModelAttribute("erreurName") String erreurName, @ModelAttribute("erreurNoIntro") String erreurNoIntro,
			@ModelAttribute("erreurDiscoBeforeIntro") String erreurDiscoBeforeIntro) {
		ModelAndView modelAndView = new ModelAndView("editComputer", "ComputerDTOEdit", new ComputerDTOEdit());
		List<Company> listCompanies = this.companyService.searchAllCompany();
		List<CompanyDTO> listCompaniesDTO = listCompanies.stream().map(c -> mapperCompany.mapFromModelToDTO(c))
				.collect(Collectors.toList());
		modelAndView.addObject("computerEdited", computerEdited);
		modelAndView.addObject("erreurName", erreurName);
		modelAndView.addObject("erreurNoIntro", erreurNoIntro);
		modelAndView.addObject("erreurDiscoBeforeIntro", erreurDiscoBeforeIntro);
		modelAndView.addObject("id", id);
		modelAndView.addObject("name", name);
		modelAndView.addObject("listCompanies", listCompaniesDTO);

		return modelAndView;
	}

	@PostMapping(value = "/EditComputer")
	protected RedirectView editComputer(@ModelAttribute("ComputerDTOEdit") ComputerDTOEdit computerDTOEdit,
			RedirectAttributes redir) {

		try {
			computerValidator.validationComputerDTOEdit(computerDTOEdit);
			Computer computer = mapperComputer.mapFromDTOEditToModel(computerDTOEdit);
			computerService.updateComputer(computer);
			redir.addFlashAttribute("computerEdited", "The computer was successfully updated");
			redir.addAttribute("computerName", computerDTOEdit.getComputerName());
			redir.addAttribute("id", computerDTOEdit.getId());
			return new RedirectView("/EditComputer", true);
		} catch (ValidatorException e) {
			LoggerCdb.logError(getClass(), e);
			showError(redir, e);
			return new RedirectView("/EditComputer", true);
		}

	}

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
