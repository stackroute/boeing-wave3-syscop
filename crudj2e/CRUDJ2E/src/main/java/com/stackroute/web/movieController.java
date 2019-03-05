package com.stackroute.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.stackroute.dao.MovieDaoImpl;
import com.stackroute.domain.Movie;

/**
 * Servlet implementation class movieController
 * 
 * @author Shri Ramya
 */
public class movieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public movieController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		MovieDaoImpl movieDaoObj = new MovieDaoImpl();
		movieDaoObj.createMovieTable();
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Incoming request 111111111111");
		MovieDaoImpl movieDaoObj = new MovieDaoImpl();
		List<Movie> movies = movieDaoObj.getAllMovies();

		request.setAttribute("MovieList", movies);
		RequestDispatcher rd = request.getRequestDispatcher("showMovies.jsp");
		rd.forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Movie movieObj= 
				new Movie(request.getParameter("mId"),request.getParameter("mName"),request.getParameter("genre"));
		MovieDaoImpl movieDaoObj = new MovieDaoImpl();
		Movie resultMovie = movieDaoObj.saveMovie(movieObj);
		request.setAttribute("MovieResult", resultMovie);
		RequestDispatcher rd = request.getRequestDispatcher("addMovie.jsp");
		rd.forward(request, response);
	}

}
