<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie-Update</title>
</head>
<body>
	<a href="index.jsp">Home</a>
	<form action="movie" method="get">
		<input type="submit" value="Show All Movies">
	</form>
	<a href="addMovie.jsp">Add Movie</a>
	<%
		String id = (String) request.getAttribute("id");
		String name = (String) request.getAttribute("name");
		String genre = (String) request.getAttribute("genre");
	%>

	<table border="1" width="500" align="center">
		<tr bgcolor="00FF7F">
			<th><b>Movie Id</b></th>
			<th><b>Movie Name</b></th>
			<th><b>Movie Genre</b></th>
		</tr>

		<%-- Arranging data in tabular form 
        --%>
		<tr>
			<form action="UDController" method="post">
				<input type="hidden" name="mId" value="<%=id%>">
				<td><%=id%></td>
				<td><input name="mName" value="<%=name%>"></td>
				<td><input name="mGenre" value="<%=genre%>"></td>
				<td><button type="submit">Update</button></td>
			</form>
		</tr>

	</table>


	<%
		String result = (String) request.getAttribute("result");
		if (result != null) {
	%>
	<h3><%=result%></h3>
	<%
		}
	%>
</body>
</html>