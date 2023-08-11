package com.Reviews.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Reviews.models.MovieDTO;

@Repository(value = "BackwardsRobotFishFromMarsAndlikesFish")
public class MovieRepoImpl implements MovieRepo {
	
	@PersistenceContext
	EntityManager entityManager;
	
	List<MovieDTO> myMovies = List.of(new MovieDTO("One", 1999, null),
	new MovieDTO("two", 1999, null),
	new MovieDTO("Three", 1999, null),
	new MovieDTO("Four", 1999, null),
	new MovieDTO("five", 1999, null));

	@Override
	public List<MovieDTO> getMovies() throws Exception{		
		return myMovies;
	}

}
