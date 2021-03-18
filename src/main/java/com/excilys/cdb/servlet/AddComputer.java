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
import com.excilys.cdb.logger.LoggerCdb;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

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

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {

		mapperCompany = new MapperCompany();
		companyService = new CompanyService();
		mapperComputer = new MapperComputer();
		computerService = new ComputerService();

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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String computerName = request.getParameter("computerName");
		String stringIntroduced = request.getParameter("introduced");

		String stringDiscontinued = request.getParameter("discontinued");
		String stringCompanyId = request.getParameter("companyId");

		ComputerDTOAdd computerDTOAdd = new ComputerDTOAdd.ComputerDTOAddBuilder(computerName)
				.introduced(stringIntroduced).discontinued(stringDiscontinued).company(stringCompanyId).build();
		Computer computer = mapperComputer.mapFromDTOAddToModel(computerDTOAdd);
		LoggerCdb.logInfo(getClass(), null);
		computerService.createComputer(computer);
		doGet(request, response);

	}

}
