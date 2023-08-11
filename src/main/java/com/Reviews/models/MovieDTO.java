package com.Reviews.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MovieDTO {
	
	private Integer movieId;
	@NotEmpty(message="{api.Movie_Name_not_empty}")
	private String name;
	@NotNull(message="{api.Director_Not_Null}")
	@Valid
	private DirectorDTO director;
	@Max(value=2022, message="{api.Invalid_release_year}")
	private int releaseYear;
	private Genera type;
	@NotNull(message="{api.Must_Inclued_Reviews}")
	@Valid
	private List<MovieReviewDTO> myReviews;
	
	
	public DirectorDTO getDirector() {
		return director;
	}
	public void setDirector(DirectorDTO director) {
		this.director = director;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Genera getType() {
		return type;
	}
	public void setType(Genera type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public List<MovieReviewDTO> getMyReviews() {
		return myReviews;
	}
	public void setMyReviews(List<MovieReviewDTO> myReviews) {
		this.myReviews = myReviews;
	}
	public MovieDTO() {
		
	}
	public MovieDTO(String name, int releaseYear, List<MovieReviewDTO> myReviews) {
		super();
		this.name = name;
		this.releaseYear = releaseYear;
		this.myReviews = myReviews;
	}
	
	

}
