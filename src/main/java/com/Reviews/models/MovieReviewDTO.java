package com.Reviews.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovieReviewDTO {
	
	private Integer id;
	@Min(value= 1, message="{api.Min_rating_one}")
	@Max(value=5, message="{api.Max_rating_five}")
	private int starRating;
	private String reviewer;
	@NotNull(message="{api.Review_needed}")
	private String review;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

}
