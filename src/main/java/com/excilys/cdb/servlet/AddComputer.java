package com.excilys.cdb.servlet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.dto.CompanyDTO;
import com.excilys.cdb.dto.ComputerDTOAdd;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.validator.ComputerValidatorAdd;

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
	private ComputerValidatorAdd computerValidatorAdd;

	@GetMapping(value = "/AddComputer")
	protected ModelAndView showAddComputer(ModelMap modelMap) {

		ModelAndView modelAndView = new ModelAndView("addComputer", "computerDTOAdd", new ComputerDTOAdd());
		if (modelMap.containsKey("computerDTOBindingResult")) {
			modelAndView.addObject("org.springframework.validation.BindingResult.computerDTOAdd",
					modelMap.get("computerDTOBindingResult"));
		}

		List<Company> listCompanies = this.companyService.searchAllCompany();
		List<CompanyDTO> listCompaniesDTO = listCompanies.stream().map(c -> mapperCompany.mapFromModelToDTO(c))
				.collect(Collectors.toList());
		modelAndView.addObject("computerAdded", modelMap.get("computerAdded"));
		modelAndView.addObject("listCompanies", listCompaniesDTO);

		return modelAndView;
	}

	@PostMapping("/AddComputer")
	protected RedirectView addComputer(@ModelAttribute("computerDTOAdd") final ComputerDTOAdd computerDTOAdd,
			BindingResult bindingResult, RedirectAttributes redir) {
		computerValidatorAdd.validate(computerDTOAdd, bindingResult);
		if (bindingResult.hasErrors()) {
			redir.addFlashAttribute("computerDTOBindingResult", bindingResult);
			return new RedirectView("/AddComputer", true);
		}
		Computer computer = mapperComputer.mapFromDTOAddToModel(computerDTOAdd);
		computerService.createComputer(computer);
		redir.addFlashAttribute("computerAdded", "The computer was successfully added");
		return new RedirectView("/AddComputer", true);

	}

}
