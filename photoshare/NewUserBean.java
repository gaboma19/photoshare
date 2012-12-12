package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.sql.Date;

/**
 * A bean that handles new user data
 *
 * @author G. Zervas <cs460tf@bu.edu>
 */
public class NewUserBean {
  private String email = "";
  private String password1 = "";
  private String password2 = "";
  public String fname = "";
  public String lname = "";
  private String dob1 = "";
  private String dob2 = "";
  private String dob3 = "";
  public String gender = "";
  public String city = "";
  public String state = "";
  public String country = "";
  public String location = "";
  public String education = "";

  public String saySomething() {
    System.out.println("Hello!");
    return "Test";
  }
  
  public String getEmail() {
    return email;
  }

  public String getPassword1() {
    return password1;
  }

  public String getPassword2() {
    return password2;
  }
  
  public String getFname() {
	return fname;
  }
  
  public String getLname() {
    return lname;
  }
  
  public String getDob1() {
	return dob1;
  }
  
  public String getDob2() {
    return dob2;
  }
  
  public String getDob3() {
    return dob3;
  }
  
  public String getGender() {
    return gender;
  }
  
  public String getCity() {
    return city;
  }
  
  public String getState() {
    return state;
  }
  
  public String getCountry() {
    return country;
  }
  
  public String getLocation() {
    return location;
  }
  
  public String getEducation() {
    return education;
  }
  
  public Date getDob() {
	return new Date(Integer.parseInt(dob1) - 1900,Integer.parseInt(dob2),Integer.parseInt(dob3));
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword1(String password1) {
    this.password1 = password1;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }
  
  public void setFname(String x) {
	this.fname = x;
  }
  
  public void setLname(String x) {
    this.lname = x;
  }
  
  public void setDob1(String x) {
	this. dob1 = x;
  }
  
  public void setDob2(String x) {
    this. dob2 = x;
  }
  
  public void setDob3(String x) {
    this. dob3 = x;
  }
  
  public void setGender(String x) {
    this. gender = x;
  }
  
  public void setCity(String x) {
    this. city = x;
  }
  
  public void setState(String x) {
    this. state = x;
  }
  
  public void setCountry(String x) {
    this. country = x;
  }
  
  public void setLocation(String x) {
    this. location = x;
  }
  
  public void setEducation(String x) {
    this. education = x;
  }
  
}
