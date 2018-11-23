package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

	public User findById(long id);
}
