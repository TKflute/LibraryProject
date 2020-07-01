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
    private static String SELECT_PATRON_BY_ID = "select * from patron where id = ?";
    private static String INSERT_PATRON = "insert into patron(accountFrozen, firstName, lastName, userName,passWord) values(?, ?, ?,?,?)";
    private static String DELETE_PATRON = "delete from patron where id = ?";
    private static String UPDATE_PATRON = "update patron set accountFrozen = ?,firstName = ?, lastName=?, userName = ?, passWord = ? where id = ?";
    
    public List<PatronModel> getAllPatronModel() {
        
        List<PatronModel> AllPatronModel = new ArrayList<PatronModel>();
        
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_Patron);
                ResultSet rs = pstmt.executeQuery() ) {
            
            while(rs.next()) { 
                
                int id = rs.getInt("id");
                boolean accountFrozen = rs.getBoolean("accountFrozen");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                
                //PatronModel patron = new PatronModel(id, passWord, firstName, lastName, userName,accountFrozen);
                //AllPatronModel.add(patron);
                AllPatronModel.add(new PatronModel(id, passWord, firstName, lastName, userName,accountFrozen));
                
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return AllPatronModel;
    }
    public PatronModel getPatrontById(int id) {
        
        PatronModel patronModel = null;
        
        try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_ID)) {
            
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            // if patron found, if statement run, if not null returned as Patron
            if(rs.next()) {
            
                boolean accountFrozen = rs.getBoolean("accountFrozen");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("userName");
                String passWord = rs.getString("passWord");
                
                 patronModel = new PatronModel(id, passWord, firstName, lastName, userName,accountFrozen);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return patronModel;
    }
    
    public boolean addPatronModel(PatronModel patronModel) {
        
        try(PreparedStatement pstmt = conn.prepareStatement(INSERT_PATRON)) {
            
            
            
            pstmt.setBoolean(1,patronModel.isAccountFrozen());
            pstmt.setString(2, patronModel.getFirstName());
            pstmt.setString(3, patronModel.getLastName());
            pstmt.setString(4, patronModel.getUserName());
            pstmt.setString(4, patronModel.getPassWord());
            
            // at least one row added
            if(pstmt.executeUpdate() > 0) {
                return true;
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deletePatronModel(int id) {
        try (PreparedStatement pstmt = conn.prepareStatement(DELETE_PATRON)) {
            pstmt.setInt(1, id);
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
            pstmt.setBoolean(1,patronModel.isAccountFrozen());
            pstmt.setString(2, patronModel.getFirstName());
            pstmt.setString(3, patronModel.getLastName());
            pstmt.setString(4, patronModel.getUserName());
            pstmt.setString(4, patronModel.getPassWord());
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
