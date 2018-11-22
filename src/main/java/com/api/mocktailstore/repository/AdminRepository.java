package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByEmail(String email);
}
