package com.cognixia.jump.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Librarian;

public class LibrarianDao {
	
	public static String FIND_LIBRARIAN_BY_ID="select * from librarian where librarian_id = ? ";
	Connection connection=ConnectionManager.getConnection();
	
    Librarian librarian;
    
	public Librarian findLibrarianById(int librarian_id) {
		try {
			PreparedStatement pstmt=connection.prepareStatement(FIND_LIBRARIAN_BY_ID);
			pstmt.setInt(1, librarian_id);
			ResultSet rs=pstmt.executeQuery();
			if (rs.next()) {
				String username=rs.getString("username");
				String password=rs.getString("password");
				librarian=new Librarian(librarian_id, username, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return librarian;
	}
}