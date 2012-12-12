<%@ page import="edu.bu.cs.cs460.photoshare.AddTagDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<%
	String tagStr = request.getParameter("tag");
	%>
<head><title>Tag <%=tagStr%></title></head>
<body>
<h1>Tag "<%=tagStr%>"</h1>

<h2>Existing pictures</h2>
<table>
    <tr>
        <%
			AddTagDao addTagDao = new AddTagDao();
            List<Integer> pictureIds = addTagDao.getTaggedAs(tagStr);
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

<p><a href="index.jsp">Home</a></p>	

</body>
</html>