package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderBy
 */
@WebServlet("/OrderBy")
public class OrderBy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderBy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String orderAttribute = request.getParameter("orderByAttribute");

		String orderAttributeSession = (String) session.getAttribute("orderAttribute");
		String sortOrderSession = (String) session.getAttribute("orderSort");
		if (orderAttribute.equals(orderAttributeSession)) {

			if ("asc".equals(sortOrderSession)) {
				session.setAttribute("orderSort", "desc");
			} else {
				session.setAttribute("orderSort", "asc");
			}
		} else {
			session.setAttribute("orderAttribute", orderAttribute);
		}
		if (request.getParameter("search") != null) {

			this.getServletContext().getRequestDispatcher("/SearchComputer").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/ListComputer").forward(request, response);
		}
	}

}
