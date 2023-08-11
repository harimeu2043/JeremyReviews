package com.Reviews.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Reviews.entities.Director;
import com.Reviews.entities.Movie;
import com.Reviews.entities.Review;
import com.Reviews.exceptions.ReviewsException;
import com.Reviews.models.Genera;
import com.Reviews.models.MovieDTO;
import com.Reviews.models.MovieReviewDTO;
import com.Reviews.repo.MovieRepo2;
import com.Reviews.repo.ReviewRepo;

@Service
@Transactional
public class MovieServiceImpl2 implements MovieService{
	
	public final int ResultsPerPage = 10;
	
	@Autowired
	MovieRepo2 repo;
	
	@Autowired
	ReviewRepo reviewRepo;

	@Override
	public List<MovieDTO> getAllMovies() throws Exception {
		Iterable<Movie> dbResults = repo.findAll();
		List<MovieDTO> dtos = new ArrayList<>();
		dbResults.forEach(m-> dtos.add(m.toDTO()));
		if (dtos.isEmpty()) {
			throw new ReviewsException("Service.No_movies");
		}
		return dtos;
	}

	@Override
	public MovieDTO getMovie(int id) throws Exception {
		Optional<Movie> option = repo.findById(id);
		Movie m = option.orElseThrow(()->new ReviewsException("Service.No_movies"));
		return m.toDTO();
	}

	@Override
	public Integer deleteMovie(int id) throws Exception {
		Optional<Movie> option = repo.findById(id);
		Movie m = option.orElseThrow(()->new ReviewsException("Service.No_movies"));
		m.setDirector(null);
		repo.delete(m);
		return m.getId();
	}

	@Override
	public MovieDTO changeType(int id, Genera newType) throws Exception {
		Optional<Movie> option = repo.findById(id);
		Movie m = option.orElseThrow(()->new ReviewsException("Service.No_movies"));
		m.setMyType(newType);
		return m.toDTO();
	}

	@Override
	public Integer addMovie(MovieDTO dto) throws Exception {
		if (dto.getMovieId() != null && repo.existsById(dto.getMovieId())) {
			throw new ReviewsException("Service.Movies_exists");
		}
		Movie m = new Movie();
		m.setId(dto.getMovieId());
		m.setMyType(dto.getType());
		m.setName(dto.getName());
		m.setReleased(dto.getReleaseYear());
		Director d = new Director();
		d.setHairColor(dto.getDirector().getHairColor());
		d.setName(dto.getDirector().getName());
		d.setId(dto.getDirector().getId());
		m.setDirector(d);
		repo.save(m);
		return m.getId();
	}
	
	public List<MovieDTO> getByGenera(Genera gen){
		List<Movie> dbResults = repo.findByMyType(gen);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());	
	}
	
	public List<MovieDTO> getbyGenera(Genera gen, int pageNo){
		Sort s = Sort.by("name");
		Pageable p = PageRequest.of(pageNo, 3, s);
		List<Movie> dbResults = repo.findByMyType(gen, p);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());	
	}

	@Override
	public List<MovieDTO> getMoviePage(int pageNo) throws Exception {
		Pageable p = PageRequest.of(pageNo, ResultsPerPage);
		Page<Movie> dbResults = repo.findAll(p);
		List<Movie> movieList = dbResults.getContent();
		List<MovieDTO> results = movieList.stream().map(m -> m.toDTO()).collect(Collectors.toList());
		
		return results;
	}

	@Override
	public Boolean addReview(MovieDTO dto) throws Exception {
		Optional<Movie> option = repo.findById(dto.getMovieId());
		Movie m = option.orElseThrow(()->new ReviewsException("Service.No_movies"));
		int counter = 0;
		for (MovieReviewDTO review: dto.getMyReviews()) {
			if (review.getId() == null) {
				counter++;
				Review r = new Review();
				r.setReview(review.getReview());
				r.setReviewer(review.getReviewer());
				r.setStarRating(review.getStarRating());
				m.getMyReviews().add(r);
			}
		}
		return counter > 0;
	}

	@Override
	public List<MovieDTO> getMoviesByName(String name) throws Exception {
		List<Movie> dbResults = repo.findByNameLike(name);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());
	}

	@Override
	public List<MovieDTO> getMoviesByYear(int year) throws Exception {
		List<Movie> dbResults = repo.findByReleased(year);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());
	
	}

	@Override
	public List<MovieDTO> getMoviesByRating(int min, int max) throws Exception {
		List<Movie> dbResults = repo.findByRatingRange(min, max);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());
	
	}

	@Override
	public List<MovieDTO> getMoviesByTop() throws Exception {
		Pageable p = PageRequest.of(0, ResultsPerPage);
		List<Movie> dbResults = repo.getTopResults(p);
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());
	}

	@Override
	public List<MovieDTO> getMovieByDirector(String director) throws Exception {
		List<Movie> dbResults = repo.getByDirectorName("%" + director + "%");
		return dbResults.stream().map(m -> m.toDTO()).collect(Collectors.toList());
	}

	@Override
	public Boolean updateReview(MovieReviewDTO dto) throws ReviewsException {
		Optional<Review> option = reviewRepo.findById(dto.getId());
		Review r = option.orElseThrow(()->new ReviewsException("no_Review"));
		r.setReview(dto.getReview());
		r.setStarRating(dto.getStarRating());
		return true;
	}

	@Override
	public Boolean deleteReview(int id) throws Exception {
		Optional<Review> option = reviewRepo.findById(id);
		Review r = option.orElseThrow(()->new ReviewsException("no_Review"));
		reviewRepo.delete(r);
		return true;
	}


}
