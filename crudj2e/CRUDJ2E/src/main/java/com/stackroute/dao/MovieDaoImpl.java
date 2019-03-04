package com.stackroute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import com.stackroute.domain.Movie;

/**
 * The MovieDaoImpl program implements all the four 
 * methods defined in MovieDao interface. Prepared 
 * statements are used and queries are executed.
 * 
 * @author  Shri Ramya
 */
public class MovieDaoImpl implements MovieDao {

	private Connection con;
	private Context ctx;
	//Establishing connection to mysql database
	public MovieDaoImpl() {
		super();
		try {
			ctx = new InitialContext();
			DataSource  dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/ds1");
			con=dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		String sql="select * from movie";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				Movie movieObj = new Movie();
				movieObj.setMovieid(rs.getString(1));
				movieObj.setMovieName(rs.getString(2));
				movieObj.setMovieGenre(rs.getString(3));
				movies.add(movieObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	public Movie saveMovie(Movie movieObj) {
		Movie movieObjOne = new Movie();
		int rs=0;
		String sql="insert into movie values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieObj.getMovieid());
			pstmt.setString(2, movieObj.getMovieName());
			pstmt.setString(3, movieObj.getMovieGenre());
			rs=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rs!=0) {
			return movieObj;
		}
		return movieObjOne;
	}

	public Movie updateMovie(Movie movieObj) {
		Movie movieObjOne = new Movie();
		int rs=0;
		String sql="update movie set movieid=?, movieName=?, movieGenre=? where movieid=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieObj.getMovieid());
			pstmt.setString(2, movieObj.getMovieName());
			pstmt.setString(3, movieObj.getMovieGenre());
			pstmt.setString(4, movieObj.getMovieid());
			rs=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rs!=0) {
			return movieObj;
		}
		return movieObjOne;
	}

	public String deleteMovie(String movieId) {
		int rs=0;
		String sql="delete from movie where movieid=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, movieId);
			rs=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=0) {
			return "success";
		}
		return null;
	}

	public void createMovieTable() {
		PreparedStatement pstmt = null;
		String sql1 = "SELECT count(*)\n" + 
				"FROM information_schema.TABLES\n" + 
				"WHERE (TABLE_SCHEMA = 'mysql') AND (TABLE_NAME = 'movie')";
		
		String sql2 = "create table movie(movieid varchar(25) not null, "
				+ "movieName varchar(100) not null, movieGenre varchar(100) not null,primary key (movieid))";
		
		try {
			pstmt = con.prepareStatement(sql1);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)==0) {
					pstmt = con.prepareStatement(sql2);
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
