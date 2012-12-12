<%@ page import="edu.bu.cs.cs460.photoshare.AddFriendDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="addFriendBean"
             class="edu.bu.cs.cs460.photoshare.AddFriendBean">
    <jsp:setProperty name="addFriendBean" property="*"/>
</jsp:useBean>

<html>
<head><title>Friends</title><head>

<body>
<h1>Add friends</h1>

<% boolean showForm = true;
   String err = null; %>

<%  AddFriendDao addFriendDao = new AddFriendDao();
	if (!addFriendBean.getFriend_id().equals("")) {
    
	   String user = request.getUserPrincipal().getName();
	   if (user != null) {
		   boolean success = addFriendDao.create(user, addFriendBean.getFriend_id());
		   if (success) {
			 showForm = false;
		   } else {
			 err = "Couldn't add friend (that friend may have already been added)";
		   }
	   } else {
		err = "Couldn't find your user_id.";
	   }
     
   }
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<% if (showForm) { %>

<h2>User list</h2>
<p>(user_id, Full Name)</p>

        <%
            List<String> users = addFriendDao.allUsers();
            for (String line : users) {
        %>

			<p><%= line %></p>

        <%
            }
        %>

<h2>Friend list</h2>
<p>(Full Name)</p>

        <%
            List<String> friends = addFriendDao.allFriends(request.getUserPrincipal().getName());
            for (String line : friends) {
        %>

			<p><%= line %></p>

        <%
            }
        %>
		
<form action="friends.jsp" method="post">
	Add Friend: <br>
	Friend's user id: <input type="text" name="friend_id"/></br>
	<input type="submit" value="Submit"/><br/>
</form>

<% }
   else { %>
   
   <p><a href="friends.jsp">Return to friends page</a>.</p>
  
<% } %> 
   
</body>
</html>
