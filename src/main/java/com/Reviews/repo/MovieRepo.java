package com.Reviews.repo;

import java.util.List;

import com.Reviews.models.MovieDTO;

public interface MovieRepo {

	List<MovieDTO> getMovies() throws Exception;

}
