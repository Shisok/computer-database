package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapperCompany mapperCompany;
	private MapperComputer mapperComputer;
	private CompanyService companyService;
	private ComputerService computerService;

	private ComputerValidator computerValidator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {

		mapperCompany = new MapperCompany();
		companyService = new CompanyService();
		mapperComputer = new MapperComputer();
		computerService = new ComputerService();
		computerValidator = ComputerValidator.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Company> listCompanies = this.companyService.searchAllCompany();
		List<CompanyDTO> listCompaniesDTO = listCompanies.stream().map(c -> mapperCompany.mapFromModelToDTO(c))
				.collect(Collectors.toList());
		request.setAttribute("listCompanies", listCompaniesDTO);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String computerName = request.getParameter("computerName");
			String stringIntroduced = request.getParameter("introduced");

			String stringDiscontinued = request.getParameter("discontinued");
			String stringCompanyId = request.getParameter("companyId");

			ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder(computerName)
					.introduced(stringIntroduced).discontinued(stringDiscontinued).company(stringCompanyId).build();
			computerValidator.validationComputerDTOAdd(computerDTOAdd);
			Computer computer = mapperComputer.mapFromDTOAddToModel(computerDTOAdd);
			computerService.createComputer(computer);
			request.setAttribute("computerAdded", "The computer was successfully added");
			doGet(request, response);
		} catch (ValidatorException e) {
			LoggerCdb.logError(getClass(), e);
			showError(request, e);
			doGet(request, response);
		}

	}

	private void showError(HttpServletRequest request, ValidatorException e) {
		if (e.getMessage().equals(ComputerValidatorError.NONAME.getMessage())) {
			request.setAttribute("erreurName", e.getMessage());
		} else if (e.getMessage().equals(ComputerValidatorError.INTROBEFOREDISCON.getMessage())) {
			request.setAttribute("erreurNoIntro", e.getMessage());
		} else if (e.getMessage().equals(ComputerValidatorError.NOINTRO.getMessage())) {
			request.setAttribute("erreurDiscoBeforeIntro", e.getMessage());
		}
	}

}
