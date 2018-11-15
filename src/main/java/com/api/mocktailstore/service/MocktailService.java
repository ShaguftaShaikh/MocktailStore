package com.api.mocktailstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.repository.MocktailRepository;

@Service("mocktailService")
public class MocktailService {

	@Autowired
	MocktailRepository mocktailRepository;
	
	public List<Mocktail> getMocktails(){
		return mocktailRepository.findAll();
	}
}
