<%@page import="com.stackroute.domain.* "%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie-Add</title>
</head>
<body>

	<a href="index.jsp">Home</a>
	<form action="movie" method="get">
		<input type="submit" value="Show All Movies">
	</form>
	<a href="addMovie.jsp">Add Movie</a>


	<form action="movie" method="post">
		MovieId: <input type="text" name="mId"> MovieName: <input
			type="text" name="mName"> Genre: <input type="text"
			name="genre"> <input type="submit">

		<%Movie mvi =  
            (Movie)request.getAttribute("MovieResult"); 
        if(mvi!=null){%>
		<h2>You added:</h2>
		<table border="1" width="500" align="center">
			<tr bgcolor="00FF7F">
				<th><b>Movie Id</b></th>
				<th><b>Movie Name</b></th>
				<th><b>Movie Genre</b></th>
			</tr>

			<%-- Arranging data in tabular form 
        --%>
			<tr>
				<td><%=mvi.getMovieid()%></td>
				<td><%=mvi.getMovieName()%></td>
				<td><%=mvi.getMovieGenre()%></td>
			</tr>

		</table>
		<%}%>
	
</body>
</html>