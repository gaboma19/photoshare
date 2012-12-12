<%@ page import="edu.bu.cs.cs460.photoshare.Picture" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="edu.bu.cs.cs460.photoshare.AddTagDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="addTagBean"
             class="edu.bu.cs.cs460.photoshare.AddTagBean">
    <jsp:setProperty name="addTagBean" property="*"/>
</jsp:useBean>

<html>
<head><title>Image</title></head>

<body>

<%
	int imgId = Integer.parseInt(request.getParameter("picture_id"));
%>

<h1>Image <%=imgId%></h1>

<img src="/photoshare/img?picture_id=<%= imgId %>"/>

<h2>Comments</h2>
<form action="img.jsp?picture_id=<%=imgId%>" method="post">
   Comment: <input type="text" name="comment"/><br>
	<input type="submit" value="Submit"/><br/>
</form>

<table>
<th>Comment</th>
<th>user_id</th>
<th>Date</th>
<%
	AddTagDao addTagDao = new AddTagDao();
	
	List<String> comments = addTagDao.allComments(imgId);
	Iterator<String> i = comments.iterator();
	while (i.hasNext()) {
%>
	<tr>
	<td><%=i.next()%></td><td><%=i.next()%></td><td><%=i.next()%></td>
	</tr>
<%
	}
%>
<table>

<h2>Add tags</h2>

<form action="img.jsp?picture_id=<%=imgId%>" method="post">
   Tag: <input type="text" name="tag"/><br>
	<input type="submit" value="Add"/><br/>
</form>

<%
	boolean success = false;
	String username = request.getUserPrincipal().getName();
	if (!addTagBean.getTag().equals("")) {
		success = addTagDao.create(addTagBean.getTag(), imgId);
	}
	
	if (!addTagBean.getComment().equals("")) {
		success = addTagDao.comment(addTagBean.getComment(), imgId, username);
	}
%>

<h2>Tags</h2>
	<%
		List<String> tags = addTagDao.allTags(imgId);
		for (String tag : tags) {
	%>
		<a href="/photoshare/tag.jsp?tag=<%=tag%>"><%=tag%></a><br><br>
	<%
		}
	%>

<p><a href="index.jsp">Home</a></p>	
</body>
</html>
