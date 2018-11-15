package com.api.mocktailstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.modals.Mocktails;
import com.api.mocktailstore.repository.MocktailRepository;

@Service("mocktailService")
public class MocktailService {

	@Autowired
	private MocktailRepository mocktailRepository;

	public List<Mocktails> getMocktails() {
		return mocktailRepository.findAll();
	}
}
