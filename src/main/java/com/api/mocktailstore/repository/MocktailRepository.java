package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.Mocktail;

public interface MocktailRepository extends JpaRepository<Mocktail, Long>{

	public Mocktail findByMocktailId(long id);
}
