package com.Reviews.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import com.Reviews.models.DirectorDTO;
import com.Reviews.models.Genera;
import com.Reviews.models.MovieDTO;
import com.Reviews.models.MovieReviewDTO;

@Entity
public class Movie {
	@Id
	@TableGenerator(name = "wombatKittens",
					table ="key_gen",
					pkColumnName="table_name",
					valueColumnName="next_val",
					pkColumnValue="movie")
	@GeneratedValue(generator="wombatKittens", strategy=GenerationType.TABLE)
	private Integer id;
	private Integer released;
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="movie_type")
	private Genera myType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="director")
	private Director director;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="movie")
	private List<Review> myReviews;
	
	public List<Review> getMyReviews() {
		return myReviews;
	}
	public void setMyReviews(List<Review> myReviews) {
		this.myReviews = myReviews;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public MovieDTO toDTO() {
		MovieDTO dto = new MovieDTO();
		dto.setName(this.name);
		dto.setReleaseYear(this.released);
		dto.setMovieId(this.id);
		dto.setType(this.myType);
		if (this.director != null) {
			DirectorDTO dDto = new DirectorDTO();
			dDto.setHairColor(this.director.getHairColor());
			dDto.setId(this.director.getId());
			dDto.setName(this.director.getName());
			dto.setDirector(dDto);
		}
		List<MovieReviewDTO> movies = new ArrayList<>();
		for (Review r:this.myReviews) {
			MovieReviewDTO rd = new MovieReviewDTO();
			rd.setId(r.getId());
			rd.setReview(r.getReview());
			rd.setReviewer(r.getReviewer());
			rd.setStarRating(r.getStarRating());
			movies.add(rd);
		}
		dto.setMyReviews(movies);
		return dto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getReleased() {
		return released;
	}
	public void setReleased(Integer released) {
		this.released = released;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Genera getMyType() {
		return myType;
	}
	public void setMyType(Genera myType) {
		this.myType = myType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
