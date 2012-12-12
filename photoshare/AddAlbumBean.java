package edu.bu.cs.cs460.photoshare;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

public class AddAlbumBean {
	private String name = "";

	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
}