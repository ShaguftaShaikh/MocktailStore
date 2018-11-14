package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.modals.Mocktails;

public interface MocktailRepository extends JpaRepository<Mocktails, Long>{

}
