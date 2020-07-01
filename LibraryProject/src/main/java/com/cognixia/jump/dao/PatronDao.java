package com.cognixia.jump.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.PatronModel;

public class PatronDao {
	
public static final Connection conn = ConnectionManager.getConnection();
    
    private static String SELECT_ALL_Patron = "select * from patron"; // add
    private static String SELECT_PATRON_BY_ID = "select * from patron where patron_id = ?";
    private static String INSERT_PATRON = "insert into patron(first_name, last_name, username, password, account_frozen) values(?,?,?,?,?)";
    private static String DELETE_PATRON = "delete from patron where patron_id = ?";
    private static String UPDATE_PATRON = "update patron set first_name = ?, last_name=?, username = ?, password = ?, account_frozen = ? where patron_id = ?";
    
    public List<PatronModel> getAllPatronModel() {
        
        List<PatronModel> AllPatronModel = new ArrayList<PatronModel>();
        
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_Patron);
                ResultSet rs = pstmt.executeQuery() ) {
            
            while(rs.next()) { 
                
                int patron_id = rs.getInt("patron_id"); 
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean account_frozen = rs.getBoolean("account_frozen");
                
                //PatronModel patron = new PatronModel(patron_id, password, first_name, last_name, username,account_frozen);
                //AllPatronModel.add(patron);
                AllPatronModel.add(new PatronModel(patron_id, first_name, last_name, username, password, account_frozen));
                
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return AllPatronModel;
    }
    public PatronModel getPatronById(int patron_id) {
        
        PatronModel patronModel = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_ID)) {
            
            pstmt.setInt(1, patron_id);
            
            ResultSet rs = pstmt.executeQuery();
            
            // if patron found, if statement run, if not null returned as Patron
            if(rs.next()) {
                
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean account_frozen = rs.getBoolean("account_frozen");
                
                patronModel = new PatronModel(patron_id, first_name, last_name, username, password, account_frozen);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return patronModel;
    }
    
    public boolean addPatronModel(PatronModel patronModel) {
        
        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_PATRON)) {
            
            pstmt.setString(1, patronModel.getFirstName());
            pstmt.setString(2, patronModel.getLastName());
            pstmt.setString(3, patronModel.getUserName());
            pstmt.setString(4, patronModel.getPassWord());
            pstmt.setBoolean(5 ,patronModel.isAccountFrozen());
            
            // at least one row added
            if(pstmt.executeUpdate() > 0) {
                return true;
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deletePatronModel(int patron_id) {
        try (PreparedStatement pstmt = conn.prepareStatement(DELETE_PATRON)) {
            pstmt.setInt(1, patron_id);
            // at least one row deleted
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return false;
    }
    
    public boolean updatePatronModel(PatronModel patronModel) {
        
        try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_PATRON)) {
        	
            pstmt.setString(1, patronModel.getFirstName());
            pstmt.setString(2, patronModel.getLastName());
            pstmt.setString(3, patronModel.getUserName());
            pstmt.setString(4, patronModel.getPassWord());
            pstmt.setBoolean(5,patronModel.isAccountFrozen());
            pstmt.setInt(6, patronModel.getId());
            // at least one row updated
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
