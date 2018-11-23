package com.api.mocktailstore.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.Rating;
import com.api.mocktailstore.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;

@Service("ratingService")
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingService.class);

	public Rating addRating(final Rating rating) {
		LOGGER.debug("Adding rating with values" + rating);
		return ratingRepository.save(rating);
	}

	public Rating getObject(final JsonNode rating) {
		ObjectMapper mapper = new ObjectMapper();
		Rating convertedObject = null;
		LOGGER.debug("Converting " + rating + " to POJO");
		try {
			convertedObject = mapper.readValue(new TreeTraversingParser(rating), Rating.class);
			LOGGER.debug("Conversion of rating json to POJO success");
		} catch (IOException e) {
			LOGGER.error("Could not process to JSON String " + e.getMessage());
		}
		return convertedObject;
	}

	public List<Rating> getRatings() {
		LOGGER.debug("getting all ratings");
		return ratingRepository.findByVisible(true);
	}
	
	public List<Rating> getRatingsByMocktailId(Mocktail mocktail){
		LOGGER.debug("getting all ratings for mocktail id: "+mocktail.getMocktailId());
		return ratingRepository.findByRatedForAndVisible(mocktail, true);
	}

	public String getJsonString(final Rating addRating) {
		LOGGER.debug("Converting " + addRating.toString() + " to JSON String");
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(addRating);
			LOGGER.debug("Converted to JSON String: " + jsonString.toString());
		} catch (JsonProcessingException e) {
			LOGGER.error("Could not process to JSON String " + e.getMessage());
		}
		return jsonString.toString();
	}
}
