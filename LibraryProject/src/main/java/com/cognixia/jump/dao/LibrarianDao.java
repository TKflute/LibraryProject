package com.cognixia.jump.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Book;

public class LibrarianDao {
	public static String SELECT_ALL_BOOKS = "select * from books";
	public static String ADD_A_BOOK = "insert into book values(?, ?, ?, ?)";
	public static String UPDATE_BOOK = "update book set title = ?, descr = ?, rented = ?, added_to_library = ? where isbn = ? ";
	Connection connection = ConnectionManager.getConnection();

	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_BOOKS);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				int isbn = rs.getInt("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				boolean rented = rs.getBoolean("rented");
				Date added_to_library = rs.getDate("added_to_library");
				allBooks.add(new Book(isbn, title, descr, rented, added_to_library));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allBooks;
	}

	public boolean addBook(Book book) {
		try (PreparedStatement pstmt = connection.prepareStatement(ADD_A_BOOK);) {
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getDescr());
			pstmt.setBoolean(3, book.isRented());
			pstmt.setDate(4, book.getAdded_to_libray());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateBook(Book book) {
		try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_BOOK);) {
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getDescr());
			pstmt.setBoolean(3, book.isRented());
			pstmt.setDate(4, book.getAdded_to_libray());
			pstmt.setInt(5, book.getIsbn());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}