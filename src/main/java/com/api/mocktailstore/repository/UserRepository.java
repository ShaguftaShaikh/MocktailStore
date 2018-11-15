package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//public Customer findByUsername(String username);
}
