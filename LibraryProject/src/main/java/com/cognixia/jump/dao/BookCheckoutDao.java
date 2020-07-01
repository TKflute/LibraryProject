package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.BookCheckout;

public class BookCheckoutDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_BOOK_BY_ISBN = "select * from book where isbn = ?";
	private static String SELECT_BOOKS_BY_PATRON_ID = "select * from book_checkout where patron_id = ?";
	
	private static String INSERT_BOOK = "insert into book_checkout(isbn, title, descr, rented, addedTolibrary) values(?, ?, ?, ?, ?)";
	private static String DELETE_BOOK = "delete from book_checkout where isbn = ?";
	private static String UPDATE_BOOK = "update book_checkout set title = ?, descr = ?, rented, addedTolibrary  where isbn = ?";
	
	
	public List<BookCheckout> getCheckedoutHistory(int patronId) {
		
		List<BookCheckout> checkedoutBooks = new ArrayList<BookCheckout>();
		
		try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOKS_BY_PATRON_ID)){ 
			
			pstmt.setInt(1, patronId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int checkoutId = rs.getInt("checkout_id");
				String isbn = rs.getString("isbn");
				Date checkedout = rs.getDate("checkedout");
				Date dueDate = rs.getDate("due_date");
				Date returned = rs.getDate("returned");
				
				String title = getBookByISBN(isbn);

				checkedoutBooks.add(new BookCheckout(checkoutId, patronId, isbn, title, checkedout, dueDate, returned));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(checkedoutBooks);
		return checkedoutBooks;
	}
	
	
	public String getBookByISBN(String isbn) {
		
		String title = null;

		try (PreparedStatement pstmt = conn.prepareStatement(SELECT_BOOK_BY_ISBN)) {

			pstmt.setString(1, isbn);

			ResultSet rs = pstmt.executeQuery();

			// if product found, if statement run, if not null returned as book title
			if (rs.next()) {

				title = rs.getString("title");

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return title;
	}

	

	
}
