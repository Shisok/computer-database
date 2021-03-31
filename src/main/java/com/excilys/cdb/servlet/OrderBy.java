package com.excilys.cdb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class OrderBy.
 */
@Component
@RequestMapping("/OrderBy")
public class OrderBy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @param request  http message
	 * @param response http message
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			request.getRequestDispatcher("/SearchComputer").forward(request, response);
		} else {
			request.getRequestDispatcher("/ListComputer").forward(request, response);
		}
	}

}
