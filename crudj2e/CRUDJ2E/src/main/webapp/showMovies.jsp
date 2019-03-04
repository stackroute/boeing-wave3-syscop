<%@page import="com.stackroute.domain.Movie , java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MovieList</title>
</head>
<body>
	<a href="index.jsp">Home</a>
	<form action="movie" method="get">
		<input type="submit" value="Show All Movies">
	</form>
	<a href="addMovie.jsp">Add Movie</a>
	<h1>Here is/are the List of all the movies you have saved:</h1>

	<table border="1" width="500" align="center">
		<tr bgcolor="00FF7F">
			<th><b>Movie Id</b></th>
			<th><b>Movie Name</b></th>
			<th><b>Movie Genre</b></th>
			<th colspan="2"><b>Actions</b></th>
		</tr>
		<%List<Movie> mvi =  
            (List<Movie>)request.getAttribute("MovieList"); 
        for(Movie m:mvi){
        
        
        %>
		<%-- Arranging data in tabular form 
        --%>
		<tr>
			<form action="UDController" method="get">
				<input type="hidden" name="mId" value="<%=m.getMovieid() %>">
				<input type="hidden" name="mName" value="<%=m.getMovieName() %>">
				<input type="hidden" name="mGenre" value="<%=m.getMovieGenre() %>">
				<td><%=m.getMovieid()%></td>
				<td><%=m.getMovieName()%></td>
				<td><%=m.getMovieGenre()%></td>
				<td><input type="submit" name="action" value="update">
				</td>
				<td><input type="submit" name="action" value="delete">
			</form>
			</td>
		</tr>
		<%}%>
	</table>


</body>
</html>