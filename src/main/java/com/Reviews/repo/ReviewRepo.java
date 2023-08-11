package com.Reviews.repo;

import org.springframework.data.repository.CrudRepository;

import com.Reviews.entities.Review;

public interface ReviewRepo extends CrudRepository<Review, Integer>{

}
