package com.stackroute.dao;

import java.util.List;

import com.stackroute.domain.Movie;

/**
 * The MovieDao program is an interface that 
 * has method signatures for CRUD operations.
 *
 * @author  Shri Ramya
 */

public interface MovieDao {
	
	public void createMovieTable();
	public List<Movie> getAllMovies() ;
	public Movie saveMovie(Movie movieObj);
	public Movie updateMovie(Movie movieObj);
	public String deleteMovie(String movieId);

}
