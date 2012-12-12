package edu.bu.cs.cs460.photoshare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Calendar;

public class AddTagDao {
	private static final String ADD_TAG_STMT = "INSERT INTO " +
		"\"Tags\" (picture_id, tag) VALUES (?, ?)";
		
	private static final String ALL_TAGS_STMT = "SELECT " +
		"\"tag\" FROM \"Tags\" WHERE \"picture_id\" = ?";
		
	private static final String GET_TAGGED_STMT = "SELECT " +
		"\"picture_id\" FROM \"Tags\" WHERE \"tag\" = ?";
		
	private static final String ADD_COMMENT_STMT = "INSERT INTO " +
		"\"Comments\" (text, user_id, date, picture_id) VALUES (?, ?, ?, ?)";

	private static final String GET_USERID_STMT = "SELECT " +
		"\"user_id\" FROM \"Users\" WHERE \"email\" = ?";
		
	private static final String GET_COMMENTS_STMT = "SELECT " +
		"\"text\", \"user_id\", \"date\" FROM \"Comments\" WHERE \"picture_id\" = ?";
		
	public boolean create(String tag, int imgId) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DbConnection.getConnection();
			stmt = conn.prepareStatement(ADD_TAG_STMT);
			stmt.setInt(1, imgId);
			stmt.setString(2, tag);
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
	
	public static List<String> allTags(int imgId) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<String> tags = new ArrayList<String>();
		
		try {
		  conn = DbConnection.getConnection();

			stmt = conn.prepareStatement(ALL_TAGS_STMT);
			stmt.setInt(1, imgId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tags.add(rs.getString(1));
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
		return tags;
	}
	
	public static List<Integer> getTaggedAs(String tag) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<Integer> tags = new ArrayList<Integer>();
		
		try {
		  conn = DbConnection.getConnection();

			stmt = conn.prepareStatement(GET_TAGGED_STMT);
			stmt.setString(1, tag);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tags.add(rs.getInt(1));
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
		return tags;
	}
	
	public boolean comment(String comment, int imgId, String username) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int user_id = 0;
		Calendar currenttime = Calendar.getInstance();
		Date date = new Date((currenttime.getTime()).getTime());
		
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
			
			stmt = conn.prepareStatement(ADD_COMMENT_STMT);
			stmt.setString(1, comment);
			stmt.setInt(2, user_id);
			stmt.setDate(3, date);
			stmt.setInt(4, imgId);
			
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
	
	public static List<String> allComments(int imgId) {
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		List<String> comments = new ArrayList<String>();
		
		//String[] type = new String[1];
		
		try {
		  conn = DbConnection.getConnection();
		  
		  stmt = conn.prepareStatement(GET_COMMENTS_STMT);
		  stmt.setInt(1, imgId);
		  rs = stmt.executeQuery();
		  while (rs.next()) {
			  comments.add(rs.getString(1));
			  comments.add(rs.getString(2));
			  comments.add(rs.getString(3));
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
		return comments;
	}
}