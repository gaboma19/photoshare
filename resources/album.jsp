<%@ page import="edu.bu.cs.cs460.photoshare.Picture" %>
<%@ page import="edu.bu.cs.cs460.photoshare.PictureDao" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="imageUploadBean"
             class="edu.bu.cs.cs460.photoshare.ImageUploadBean">
    <jsp:setProperty name="imageUploadBean" property="*"/>
</jsp:useBean>

<html>
<head><title>Album</title></head>

<body>

<%
	int albumId = Integer.parseInt(request.getParameter("album_id"));
%>

<h1>Album <%=albumId%></h1>

<h2>Upload a new picture</h2>

<form action="album.jsp?album_id=<%=albumId%>" enctype="multipart/form-data" method="post">
   Filename: <input type="file" name="filename"/><br>
   Caption: <input type="text" name="caption"/><br>
<input type="submit" value="Upload"/><br/>
</form>

<%
    PictureDao pictureDao = new PictureDao();
    try {
        Picture picture = imageUploadBean.upload(request);
        if (picture != null) {
            pictureDao.save(picture, albumId);
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }
%>

<h2>Pictures in this album</h2>
<table>
    <tr>
        <%
			List<Integer> pictureIds = pictureDao.picturesInAlbum(albumId);
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