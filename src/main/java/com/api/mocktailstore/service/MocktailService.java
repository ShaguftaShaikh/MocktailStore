package com.api.mocktailstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.repository.MocktailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("mocktailService")
public class MocktailService {

	@Autowired
	private MocktailRepository mocktailRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(MocktailService.class);

	public List<Mocktail> getMocktails() {
		LOGGER.debug("fetching mocktail details");
		return mocktailRepository.findAll();
	}

	public Mocktail addMocktail(final Mocktail mocktail) {
		LOGGER.debug("Adding mocktail with details " + mocktail);
		return mocktailRepository.save(mocktail);
	}

	public Mocktail getMocktailById(final long id) {
		LOGGER.debug("finding mocktail by id: " + id);
		return mocktailRepository.findByMocktailId(id);
	}

	public String getJsonString(final Mocktail mocktail) {
		LOGGER.debug("Converting " + mocktail.toString() + " to JSON String");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(mocktail);
			LOGGER.debug("Converted to JSON String: " + jsonString.toString());
		} catch (JsonProcessingException e) {
			LOGGER.error("Could not process to JSON String " + e.getMessage());
		}
		return jsonString.toString();
	}

}
