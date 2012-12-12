package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Calendar;

public class AddAlbumDao {
	private static final String GET_USERID_STMT = "SELECT " +
		"\"user_id\" FROM \"Users\" WHERE \"email\" = ?";
	
	private static final String NEW_ALBUM_STMT = "INSERT INTO " +
		"\"Albums\" (name, user_id, date) VALUES (?, ?, ?)";
	
	private static final String ALL_ALBUM_IDS_STMT = "SELECT \"album_id\" FROM \"Albums\" WHERE \"user_id\" = ? ORDER BY \"album_id\" DESC";
	
	int user_id = 0;
	
	public boolean create(String username, String album_name) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
		  conn = DbConnection.getConnection();
		  stmt = conn.prepareStatement(GET_USERID_STMT);
		  stmt.setString(1, username);
		  rs = stmt.executeQuery();
		  if (rs.next()) {
			user_id = rs.getInt(1);
		  } else {
			return false;
		  }
		  
		  Calendar currenttime = Calendar.getInstance();
		  Date date = new Date((currenttime.getTime()).getTime());
		  
		  stmt = conn.prepareStatement(NEW_ALBUM_STMT);
		  stmt.setString(1, album_name);
		  stmt.setInt(2, user_id);
		  stmt.setDate(3, date);
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
	
	public List<Integer> allAlbums(String username) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<Integer> albumIds = new ArrayList<Integer>();
		
		try {
		  conn = DbConnection.getConnection();
		  stmt = conn.prepareStatement(GET_USERID_STMT);
		  stmt.setString(1, username);
		  rs = stmt.executeQuery();
		  if (rs.next()) {
			user_id = rs.getInt(1);
		  }
		  
		  conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(ALL_ALBUM_IDS_STMT);
			stmt.setInt(1, user_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				albumIds.add(rs.getInt(1));
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
		return albumIds;
	}
}