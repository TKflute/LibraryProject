package com.cognixia.jump.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.model.PatronModel;

@WebServlet("/")
public class PatronServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PatronDao patronDao;

	public void init() {

		patronDao = new PatronDao();
	}

	public void destroy() {

		try {
			ConnectionManager.getConnection().close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		switch (action) {
		case "/list":
			listPatronModel(request, response);
			break;
		case "/delete":
			deletePatronModel(request, response);
			break;
		case "/edit":
			goToEditPatronForm(request, response);
			break;
		case "/update":
			updatePatronModel(request, response);
			break;
		case "/new":
			goToNewPatronForm(request, response);
			break;
		case "/add":
			addNewPatronModel(request, response);
			break;

		default:

			response.sendRedirect("/");
			break;
		}

	}

	private void listPatronModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PatronModel> allPatronModel = patronDao.getAllPatronModel();

		request.setAttribute("allPatronModel", allPatronModel);

		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-list.jsp");

		dispatcher.forward(request, response);
	}

	private void deletePatronModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		patronDao.deletePatronModel(id);

		response.sendRedirect("list");
	}

	private void goToEditPatronForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		PatronModel patronModel = patronDao.getPatronById(id);

		request.setAttribute("patronModel", patronModel);

		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-form.jsp");

		dispatcher.forward(request, response);
	}

	private void updatePatronModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id").trim());
		boolean accountFrozen = Boolean.parseBoolean(request.getParameter("accountFrozen").trim());

		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String userName = request.getParameter("userName").trim();
		String passWord = request.getParameter("password").trim();

		PatronModel patronModel = new PatronModel(id, passWord, firstName, lastName, userName, accountFrozen);

		patronDao.updatePatronModel(patronModel);

		response.sendRedirect("list");
	}

	private void goToNewPatronForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-form.jsp");

		dispatcher.forward(request, response);
	}

	private void addNewPatronModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean accountFrozen = Boolean.parseBoolean(request.getParameter("accountFrozen").trim());

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");

		PatronModel patronModel = new PatronModel(0, passWord, firstName, lastName, userName, accountFrozen);

		patronDao.updatePatronModel(patronModel);

		response.sendRedirect("list");

	}

}