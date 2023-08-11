package com.Reviews.service;

import java.util.List;

import javax.validation.Valid;

import com.Reviews.exceptions.ReviewsException;
import com.Reviews.models.Genera;
import com.Reviews.models.MovieDTO;
import com.Reviews.models.MovieReviewDTO;

public interface MovieService {

	List<MovieDTO> getAllMovies() throws Exception;
	List<MovieDTO> getMoviePage(int pageNo) throws Exception;
	MovieDTO getMovie(int id) throws Exception;
	Integer deleteMovie(int id) throws Exception;
	MovieDTO changeType(int id, Genera newType) throws Exception;
	Integer addMovie(MovieDTO dto) throws Exception;
	Boolean addReview(MovieDTO dto) throws Exception;
	List<MovieDTO> getMoviesByName(String name) throws Exception;
	List<MovieDTO> getMoviesByYear(int year) throws Exception;
	List<MovieDTO> getMoviesByRating(int min, int max) throws Exception;
	List<MovieDTO> getMoviesByTop() throws Exception;
	List<MovieDTO> getMovieByDirector(String director) throws Exception;
	Boolean updateReview(MovieReviewDTO dto) throws ReviewsException;
	Boolean deleteReview(int id) throws Exception;
	
}
