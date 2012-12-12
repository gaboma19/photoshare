package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;

public class AddFriendBean {
	private String user_id = "";
	private String friend_id = "";
	
	public String getUser_id() {
		return user_id;
	}
	
	public String getFriend_id() {
		return friend_id;
	}
	
	public void setUser_id(String id) {
		user_id = id;
	}
	
	public void setFriend_id(String id) {
		friend_id =  id;
	}
}