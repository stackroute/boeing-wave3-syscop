package com.stackroute.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stackroute.dao.MovieDaoImpl;
import com.stackroute.domain.Movie;

/**
 * Servlet implementation class UDController
 * 
 * @author Shri Ramya
 */
public class UDController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UDController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String idSelected = (String) request.getParameter("mId");
		String nameSelected = (String) request.getParameter("mName");
		String genreSelected = (String) request.getParameter("mGenre");

		if(request.getParameter("action").equals("update")) {
			request.setAttribute("id", idSelected);
			request.setAttribute("name", nameSelected);
			request.setAttribute("genre", genreSelected);
			RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
		}else if(request.getParameter("action").equals("delete")) {
			MovieDaoImpl movieDaoObj = new MovieDaoImpl();
			movieDaoObj.deleteMovie(idSelected);
			movieController movieControllerObj = new movieController();
			movieControllerObj.doGet(request, response);
			//			RequestDispatcher rd = request.getRequestDispatcher("delete.jsp");
			//			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieDaoImpl movieDaoImplObj =  new MovieDaoImpl();
		String idSelected = (String) request.getParameter("mId");
		String nameSelected = (String) request.getParameter("mName");
		String genreSelected = (String) request.getParameter("mGenre");

		Movie movieObj =
				new Movie(idSelected,nameSelected,genreSelected);
		Movie resultMovie = movieDaoImplObj.updateMovie(movieObj);
		if(resultMovie!=null) {
			request.setAttribute("result", "Update Successfull!");
			request.setAttribute("id", idSelected);
			request.setAttribute("name", nameSelected);
			request.setAttribute("genre", genreSelected);
		}else {
			request.setAttribute("result", "Update Unsuccessfull!");
		}

		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response);
		//doGet(request, response);
	}

}
