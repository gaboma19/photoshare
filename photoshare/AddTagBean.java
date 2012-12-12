package edu.bu.cs.cs460.photoshare;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

public class AddTagBean {
	private String tag = "";
	private String comment = "";
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String c) {
		comment = c;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String n) {
		tag = n;
	}
}