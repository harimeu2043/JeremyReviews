package com.Reviews.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Review {
	@Id
	@TableGenerator(name = "pkGen",
					table ="key_gen",
					pkColumnName="table_name",
					valueColumnName="next_val",
					pkColumnValue="review")
	@GeneratedValue(generator="pkGen", strategy=GenerationType.TABLE)
	private Integer id;
	private int starRating;
	private String reviewer;
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
