package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.modals.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	public Visitor findByUsername(String username);

}
