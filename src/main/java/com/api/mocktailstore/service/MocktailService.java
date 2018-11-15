package com.api.mocktailstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.repository.MocktailRepository;

@Service("mocktailService")
public class MocktailService {

	@Autowired
<<<<<<< HEAD
	private MocktailRepository mocktailRepository;

	public List<Mocktails> getMocktails() {
=======
	MocktailRepository mocktailRepository;
	
	public List<Mocktail> getMocktails(){
>>>>>>> upstream/master
		return mocktailRepository.findAll();
	}
}
