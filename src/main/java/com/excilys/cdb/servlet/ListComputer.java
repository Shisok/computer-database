package com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.cdb.dto.ComputerDTOList;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.service.PageService;

/**
 * Servlet implementation class ListComputer.
 */
@Component
@RequestMapping("/ListComputer")
public class ListComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private PageService pageService;
	@Autowired
	private MapperComputer mapperComputer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request  http message
	 * @param response http message
	 */
	@GetMapping
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Page<Computer> page = new Page<Computer>();
		HttpSession session = request.getSession();
		setOrderBy(page, session);
		int nbComputer = countComputer(request);
		setObjectPerPage(page, session);
		int objectPerPage = page.getObjetPerPage();
		int pageMax = nbComputer / objectPerPage;
		if (nbComputer % objectPerPage != 0) {
			pageMax += 1;
		}
		setPageInt(request, page, session);
		setIndexDebutFin(page, session, pageMax);
		request.setAttribute("pageMax", pageMax);
		page.setContentPage(this.pageService.searchAllComputerPagination(page));
		List<ComputerDTOList> listeComputers = page.getContentPage().stream()
				.map(c -> mapperComputer.mapFromModelToDTOList(c)).collect(Collectors.toList());
		request.setAttribute("listeComputers", listeComputers);

		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

	}

	private void setOrderBy(Page<Computer> page, HttpSession session) {
		if (session.getAttribute("orderAttribute") != null) {

			page.setOrderAttribute((String) session.getAttribute("orderAttribute"));
		} else {
			session.setAttribute("orderAttribute", "id");
		}
		if (session.getAttribute("orderSort") != null) {
			page.setOrderSort((String) session.getAttribute("orderSort"));
		} else {
			session.setAttribute("orderSort", "asc");
		}

	}

	private void setIndexDebutFin(Page<Computer> page, HttpSession session, int pageMax) {
		page.setIndex(pageMax);
		session.setAttribute("indexDebut", page.getIndexDebut());
		session.setAttribute("indexFin", page.getIndexFin());
	}

	private void setPageInt(HttpServletRequest request, Page<Computer> page, HttpSession session) {
		String stringNumeroDePage = request.getParameter("pageno");
		if (stringNumeroDePage == null) {
			session.setAttribute("pageno", 1);
			stringNumeroDePage = "1";
		}
		int numeroPage = Integer.parseInt(stringNumeroDePage);
		request.setAttribute("numeroPage", numeroPage);
		page.setPageInt(numeroPage - 1);

	}

	private void setObjectPerPage(Page<Computer> page, HttpSession session) {
		String stringNombreObjet = (String) session.getAttribute("nbObject");
		if (stringNombreObjet == null) {
			stringNombreObjet = "10";
		}
		page.setObjetPerPage(Integer.parseInt(stringNombreObjet));
	}

	private int countComputer(HttpServletRequest request) {
		int nbComputer = this.computerService.countComputer();
		request.setAttribute("countComputer", nbComputer + "");
		return nbComputer;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request  http message
	 * @param response http message
	 */
	@PostMapping
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String stringNombreObjet = request.getParameter("nbObject");
		session.setAttribute("nbObject", stringNombreObjet);
		doGet(request, response);
	}

}
