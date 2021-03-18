package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.cdb.dto.ComputerDTOList;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.PageService;

/**
 * Servlet implementation class ListComputer
 */
@WebServlet("/ListComputer")
public class ListComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	private PageService pageService;
	private MapperComputer mapperComputer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListComputer() {
		super();
		this.computerService = new ComputerService();
		this.pageService = new PageService();
		this.mapperComputer = new MapperComputer();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<Computer> page = new Page<Computer>();
		HttpSession session = request.getSession();
		int nbComputer = this.computerService.countComputer();
		request.setAttribute("countComputer", nbComputer + "");

		String stringNombreObjet = (String) session.getAttribute("nbObject");

		if (stringNombreObjet == null) {
			stringNombreObjet = "10";
		}
		page.setObjetPerPage(Integer.parseInt(stringNombreObjet));
		int objectPerPage = page.getObjetPerPage();
		int pageMax = nbComputer / objectPerPage;

		String stringNumeroDePage = request.getParameter("pageno");
		if (stringNumeroDePage == null) {
			session.setAttribute("pageno", 1);
			stringNumeroDePage = "1";
		}
		int numeroPage = Integer.parseInt(stringNumeroDePage);
		page.setPageInt(numeroPage);
		page.setIndex(pageMax);
		session.setAttribute("indexDebut", page.getIndexDebut());
		session.setAttribute("indexFin", page.getIndexFin());

		page.setContentPage(this.pageService.searchAllComputerPagination(numeroPage - 1, objectPerPage));
		List<ComputerDTOList> listeComputers = page.getContentPage().stream().map(c -> mapperComputer.mapFromModelToDTOList(c))
				.collect(Collectors.toList());
		request.setAttribute("listeComputers", listeComputers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Page<Computer> page = new Page<Computer>();
		HttpSession session = request.getSession();
		int nbComputer = this.computerService.countComputer();
		request.setAttribute("countComputer", nbComputer + "");

		String stringNombreObjet = request.getParameter("nbObject");
		if (stringNombreObjet == null) {
			stringNombreObjet = "10";
		}
		session.setAttribute("nbObject", stringNombreObjet);
		page.setObjetPerPage(Integer.parseInt(stringNombreObjet));
		int objectPerPage = page.getObjetPerPage();
		int pageMax = nbComputer / objectPerPage;

		String stringNumeroDePage = request.getParameter("pageno");
		if (stringNumeroDePage == null) {
			session.setAttribute("pageno", 1);
			stringNumeroDePage = "1";
		}
		int numeroPage = Integer.parseInt(stringNumeroDePage);
		page.setPageInt(numeroPage);
		page.setIndex(pageMax);
		session.setAttribute("indexDebut", page.getIndexDebut());
		session.setAttribute("indexFin", page.getIndexFin());

		page.setContentPage(this.pageService.searchAllComputerPagination(numeroPage - 1, objectPerPage));
		List<ComputerDTOList> listeComputers = page.getContentPage().stream().map(c -> mapperComputer.mapFromModelToDTOList(c))
				.collect(Collectors.toList());
		request.setAttribute("listeComputers", listeComputers);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

}
