package com.cognixia.jump.controller;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.model.Book;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookDao bookDao;
	private PatronDao patronDao;
	private LibrarianDao librarianDao;

	public void init() {
		
		patronDao = new PatronDao();
		librarianDao = new LibrarianDao();
		bookDao = new BookDao();
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
		case "/librarian":
			goToLibrarian(request, response);
			break;
		case "/patron":
			goToPatron(request, response);
			break;
		case "/checkout-list":
			goToBookList(request, response);
			break;
		case "/add":
			addNewBook(request, response);
			break;
		default:
			break;
			
		}
		
	}
	
	private void goToLibrarian(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Librarian.jsp");
		
		dispatcher.forward(request, response);
		
		
	}
	
	private void goToPatron(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Patron.jsp");
		
		dispatcher.forward(request, response);
		
		
	}
	
	private void goToBookList(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		List<Book> allBooks = bookDao.getAllBooks();
		System.out.println("All books: " + allBooks);
		
		request.setAttribute("allBook", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("book-list.jsp");
		
		dispatcher.forward(request, response);
		
		
	}
	
	private void addNewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		//Grab values to create product from our form
		String isbn = request.getParameter("isbn").trim();
		String title = request.getParameter("title").trim();
		String descr = request.getParameter("descr").trim();
		
		//Create object for our product
		Book book = new Book(isbn, title, descr, false, new Date(System.currentTimeMillis()));
		
		//Call Dao to add product to our database
		bookDao.addBook(book);
		
		//Redirect to our product list
		response.sendRedirect("book-list");
	}
	
	
	

	
}
