package com.Reviews.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Reviews.exceptions.ReviewsException;
import com.Reviews.models.MovieDTO;
import com.Reviews.models.MovieReviewDTO;
import com.Reviews.service.MovieService;

@RestController
@RequestMapping(value = "movies")
@Validated
public class MovieAPI {
	
	@Autowired
	private MovieService serv;
	
	@Autowired
	private Environment env;
	
	@PostMapping("")
	public ResponseEntity<Integer> addMovie(@Valid @RequestBody MovieDTO dto) throws Exception{
		Integer id = serv.addMovie(dto);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}
	
	@PutMapping("")
	public ResponseEntity<Boolean> addReview(@Valid @RequestBody MovieDTO dto) throws Exception{
		Boolean didAdd = serv.addReview(dto);
		if (didAdd) {
			return new ResponseEntity<>(didAdd, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(didAdd, HttpStatus.ALREADY_REPORTED);
		
	}
	@GetMapping("")
	public ResponseEntity<List<MovieDTO>> getMovies() throws Exception{
		List<MovieDTO> myMovies = serv.getAllMovies();
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	@GetMapping("/{pageNo}")
	public ResponseEntity<List<MovieDTO>> getMoviePage(@PathVariable int pageNo) throws Exception{
		List<MovieDTO> myMovies = serv.getMoviePage(pageNo);
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	@GetMapping("/name/{name}")
	public ResponseEntity<List<MovieDTO>> getMoviesByname(@PathVariable String name) throws Exception{
		List<MovieDTO> myMovies = serv.getMoviesByName(name);
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	
	@GetMapping("/year/{year}")
	public ResponseEntity<List<MovieDTO>> getMoviesByYear(@PathVariable int year) throws Exception{
		List<MovieDTO> myMovies = serv.getMoviesByYear(year);
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	
	@GetMapping("/rating/{min}/{max}")
	public ResponseEntity<List<MovieDTO>> getMoviesByRating(@PathVariable int min, @PathVariable int max) throws Exception{
		List<MovieDTO> myMovies = serv.getMoviesByRating(min, max);
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	
	@GetMapping("/top")
	public ResponseEntity<List<MovieDTO>> getMoviesByTop() throws Exception{
		List<MovieDTO> myMovies = serv.getMoviesByTop();
		return new ResponseEntity<>(myMovies, HttpStatus.OK);
	}
	@GetMapping("/id/{movieId}")
	public ResponseEntity<MovieDTO> getOneMovie(@PathVariable @Min(value=1, message="{api.invalid_movie_id}") Integer movieId) throws Exception{
		MovieDTO dto = serv.getMovie(movieId);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@PutMapping("/review")
	public ResponseEntity<Boolean> updateReview(@RequestBody MovieReviewDTO dto) throws ReviewsException{
		Boolean didUpdate = serv.updateReview(dto);
		return new ResponseEntity<>(didUpdate, HttpStatus.OK);
	}
	@GetMapping("/director/{director}")
	public ResponseEntity<List<MovieDTO>> getByDirector(@PathVariable String director) throws Exception{
		List<MovieDTO> dto = serv.getMovieByDirector(director);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	@DeleteMapping("/{movieId}")
	public ResponseEntity<Integer> removeMovie(@PathVariable Integer movieId) throws Exception{
		Integer results = serv.deleteMovie(movieId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	@DeleteMapping("/review/{id}")
	public ResponseEntity<Boolean> removeReview(@PathVariable int id) throws Exception{
		Boolean didDelete = serv.deleteReview(id);
		return new ResponseEntity<>(didDelete, HttpStatus.OK);
	}
	
}
