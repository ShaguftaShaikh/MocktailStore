package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}
