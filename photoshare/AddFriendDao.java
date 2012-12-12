package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddFriendDao {
	private static final String GET_USERID_STMT = "SELECT " +
		"\"user_id\" FROM \"Users\" WHERE \"email\" = ?";
		
	private static final String CHECK_FRIEND_STMT = "SELECT " +
      "COUNT(*) FROM \"Friends\" WHERE (user_id, friend_id) = (?, ?)";
	  
	private static final String GET_FRIEND_STMT = "SELECT " +
      "\"fname\", \"lname\" FROM \"Users\" AS u, \"Friends\" AS f WHERE f.user_id = ? AND f.friend_id=u.user_id";
	
	private static final String NEW_FRIEND_STMT = "INSERT INTO " +
		"\"Friends\" (user_id, friend_id) VALUES (?, ?)";	

	private static final String ALL_USERS_STMT = "SELECT \"user_id\", \"fname\", \"lname\" FROM \"Users\" ORDER BY \"lname\" DESC";		
		
	public boolean create(String username, String friend_id) {
    PreparedStatement stmt = null;
	PreparedStatement stmt2 = null;
    Connection conn = null;
    ResultSet rs = null;
	int user_id = 0;
	int f_id = Integer.parseInt(friend_id);
    try {
	  conn = DbConnection.getConnection();
	  stmt2 = conn.prepareStatement(GET_USERID_STMT);
	  stmt2.setString(1, username);
	  rs = stmt2.executeQuery();
	  if (rs.next()) {
		user_id = rs.getInt(1);
	  } else {
		return false;
	  }
	  
	  stmt2 = conn.prepareStatement(CHECK_FRIEND_STMT);
	  stmt2.setInt(1, user_id);
	  stmt2.setInt(2, f_id);
	  rs = stmt2.executeQuery();
	  if (!rs.next()) {
        // Theoretically this can't happen, but just in case...
        return false;
      }
      int result = rs.getInt(1);
      if (result > 0) {
        // This friend is already added.
        return false; 
      }
	
      stmt = conn.prepareStatement(NEW_FRIEND_STMT);
      stmt.setInt(1, user_id);
      stmt.setInt(2, f_id);
      stmt.executeUpdate();
	  
	  stmt.setInt(1, f_id);
	  stmt.setInt(2, user_id);
	  stmt.executeUpdate();

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    } finally {
      if (rs != null) {
        try { rs.close(); }
        catch (SQLException e) { ; }
        rs = null;
      }
      
      if (stmt != null) {
        try { stmt.close(); }
        catch (SQLException e) { ; }
        stmt = null;
      }
      
      if (conn != null) {
        try { conn.close(); }
        catch (SQLException e) { ; }
        conn = null;
      }
    }
  }
  
  public List<String> allUsers() {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<String> users = new ArrayList<String>();
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(ALL_USERS_STMT);
			rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			rs.close();
			rs = null;

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return users;
	}
	
	public List<String> allFriends(String username) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int user_id = 0;
		
		List<String> friends = new ArrayList<String>();
		try {
			conn = DbConnection.getConnection();
			
			stmt = conn.prepareStatement(GET_USERID_STMT);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user_id = rs.getInt(1);
			}
			
			stmt = conn.prepareStatement(GET_FRIEND_STMT);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				friends.add(rs.getString(1) + " " + rs.getString(2));
			}

			rs.close();
			rs = null;

			stmt.close();
			stmt = null;

			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}

		return friends;
	}
	
}