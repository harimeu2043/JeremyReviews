package com.Reviews.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.Reviews.entities.Movie;
import com.Reviews.models.Genera;

public interface MovieRepo2 extends PagingAndSortingRepository<Movie, Integer>{
	
	List<Movie> findByMyType(Genera theType);
	List<Movie> findByMyType(Genera theType, Pageable p);
	List<Movie> findByNameAndReleasedLessThan(String name, Integer cutOff);
	@Query("Select m from Movie m Where m.name LIKE CONCAT('%',:name,'%')")
	List<Movie> findByNameLike(@Param("name")String name);
	List<Movie> findByReleased(int year);
	@Query("SELECT m FROM Movie m LEFT JOIN m.myReviews r GROUP BY m Having avg(r.starRating) > :min and avg(r.starRating) < :max")
	List<Movie> findByRatingRange(@Param("min")int min,@Param("max") int max);
	@Query(value="SELECT m FROM Movie m LEFT JOIN m.myReviews r GROUP BY m ORDER BY AVG(r.starRating) desc",nativeQuery=true)
	List<Movie> getTopResults(Pageable p);
	@Query("Select m From Movie m where m.director.name like :name")
	List<Movie> getByDirectorName(@Param("name")String name);

	//@Query("SELECT m.name FROM Movie M where m.releaseYear = :releaseYear and m.myType = :type")
	//List<String> batman(@Param("releaseYear")Integer releaseYear,@Param("type") Genera type);
}
