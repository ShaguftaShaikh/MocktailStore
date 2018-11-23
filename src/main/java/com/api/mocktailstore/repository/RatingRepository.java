package com.api.mocktailstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	public List<Rating> findByVisible(boolean visible);
	public List<Rating> findByRatedForAndVisible(Mocktail mocktail,boolean visible);
}
