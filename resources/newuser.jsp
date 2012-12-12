<%--
  Author: Giorgos Zervas <cs460tf@cs.bu.edu>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.bu.cs.cs460.photoshare.NewUserDao" %>
<jsp:useBean id="newUserBean"
             class="edu.bu.cs.cs460.photoshare.NewUserBean" />
<jsp:setProperty name="newUserBean" property="*"/>

<html>
<head><title>New User</title></head>

<body>
<!-- We want to show the form unless we successfully create a new user -->
<% boolean showForm = true;
   String err = null; %>

<% if (!newUserBean.getEmail().equals("")) {
     if (!newUserBean.getPassword1().equals(newUserBean.getPassword2())) {
       err = "Both password strings must match";


     }
     else if (newUserBean.getPassword1().length() < 4) {
       err = "Your password must be at least four characters long";
     }
     else {
       // We have valid inputs, try to create the user
       NewUserDao newUserDao = new NewUserDao();
       boolean success = newUserDao.create(newUserBean.getEmail(),
             newUserBean.getPassword1(),
			 newUserBean.fname,
			 newUserBean.lname,
			 newUserBean.getDob(),
			 newUserBean.gender,
			 newUserBean.city,
			 newUserBean.state,
			 newUserBean.country,
			 newUserBean.location,
			 newUserBean.education);
       if (success) {
         showForm = false;
       } else {
         err = "Couldn't create user (that email may already be in use)";
       }
     }
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (showForm) { %>

<h2>New user info</h2>

<form action="newuser.jsp" method="post">
  First name: <input type="text" name="fname"/> Last name: <input type="text" name="lname"/><br>
  Email: <input type="text" name="email"/><br>
  Date of birth (YYYY-MM-DD): <input type="text" name="dob1" size="4" maxlength="4"/><input type="text" name="dob2" size="2" maxlength="2"/><input type="text" name="dob3" size="2" maxlength="2"/><br>
  Gender: <input type="text" name="gender" size="1" maxlength="1"/><br>
  Hometown: City <input type="text" name="city"/> State <input type="text" name="state" size="2" maxlength="2"/> Country <input type="text" name="country"/><br>
  Location: <input type="text" name="location"/><br>
  Education: <input type="text" name="education"/><br>
  Password: <input type="password" name="password1"/><br>
  Re-enter password: <input type="password" name="password2"/><br>
  <input type="submit" value="Create"/><br/>
</form>

<% }
   else { %>

<h2>Success!</h2>

<p>A new user has been created with email <%= newUserBean.getEmail() %>.
You can now return to the <a href="login.jsp">login page</a>.

<% } %>

</body>
</html>
