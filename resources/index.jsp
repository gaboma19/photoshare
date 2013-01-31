
<%@ page import="edu.bu.cs.cs460.photoshare.Picture" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.AddAlbumDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="addAlbumBean"
             class="edu.bu.cs.cs460.photoshare.AddAlbumBean">
    <jsp:setProperty name="addAlbumBean" property="*"/>
</jsp:useBean>

<html>
<head><title>Photo sharing</title></head>

<body>
<h1>A skeleton photo sharing application for CS460/660 PA2</h1>

Hello <b><code><%= request.getUserPrincipal().getName() %></code></b>, click here to
<a href="/photoshare/logout.jsp">log out</a>.

<h2>Create album</h2>

<form action="index.jsp" method="post">
    Album Name: <input type="text" name="name"/>
    <input type="submit" value="Create"/><br/>
</form>

<%
	String err = null;
	AddAlbumDao addAlbumDao = new AddAlbumDao();
	if (!addAlbumBean.getName().equals("")) {
		String user = request.getUserPrincipal().getName();
		if (user != null) {
		   boolean success = addAlbumDao.create(user, addAlbumBean.getName());

	   } else {
		err = "Couldn't find your user_id.";
	   }
	}
%>

<% if (err != null) { %>
<font color=red><b>Error: <%= err %></b></font>
<% } %>

<h2>Your albums</h2>
	<% List<Integer> albums = addAlbumDao.allAlbums(request.getUserPrincipal().getName());
	   for (Integer albumId : albums) { 
	%>
		<a href="/photoshare/album.jsp?album_id=<%= albumId %>"><%= albumId %></a><br><br>
	<%
        }
    %>

<h2>Existing pictures</h2>
<table>
    <tr>
        <%
			PictureDao pictureDao = new PictureDao();
            List<Integer> pictureIds = pictureDao.allPicturesIds();
            for (Integer pictureId : pictureIds) {
        %>
        <td><a href="/photoshare/img.jsp?picture_id=<%= pictureId %>">
            <img src="/photoshare/img?t=1&picture_id=<%= pictureId %>"/>
        </a>
        </td>
        <%
            }
        %>
    </tr>
</table>

<h2>Friends</h2>

<p><a href="/photoshare/friends.jsp">Add friends</a>.</p>

<h2>Tags</h2>

<p><a href="/photoshare.

</body>
</html>
