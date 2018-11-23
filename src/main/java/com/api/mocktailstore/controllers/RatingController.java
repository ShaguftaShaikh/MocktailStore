package com.api.mocktailstore.controllers;

import java.util.Objects;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.Rating;
import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.service.MocktailService;
import com.api.mocktailstore.service.RatingService;
import com.api.mocktailstore.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin
@RestController
@RequestMapping("/rating")
public class RatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

	@Autowired
	private RatingService ratingService;

	@Autowired
	private MocktailService mocktailService;

	@Autowired
	private UserService userService;

	/**
	 * Find mocktail by id Find User by id Add rating map rating to the mocktail
	 * 
	 * @param mocktailRating
	 * @return updatedMocktail
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> addRating(@RequestBody JsonNode mocktailRating) {
		LOGGER.info("Received a request to add rating for mocktail:" + mocktailRating);
		ResponseEntity<String> response = null;
		JSONObject message = new JSONObject();
		if (!mocktailRating.isNull()) {
			JsonNode mocktailIdPath = mocktailRating.path("mocktailId");
			long mocktailId = mocktailIdPath.asLong();
			LOGGER.info("finding mocktail with id: " + mocktailId);
			Mocktail mocktail = mocktailService.getMocktailById(mocktailId);

			if (mocktail != null && !Objects.isNull(mocktail)) {
				JsonNode userIdPath = mocktailRating.path("userId");
				long userId = userIdPath.asLong();
				LOGGER.info("finding user with id: " + userId);
				User user = userService.getUserById(userId);
				if (user != null && !Objects.isNull(user)) {
					ObjectNode parsedNode = (ObjectNode) mocktailRating;
					parsedNode.remove("mocktailId");
					parsedNode.remove("userId");

					LOGGER.info("Converting JSON to POJO");
					Rating convertedRating = ratingService.getObject((JsonNode)parsedNode);
					convertedRating.setRatedBy(user);
					
					//System.out.println("---------"+convertedRating);
					
					LOGGER.info("Adding rating data to rating table");
					Rating addRating = ratingService.addRating(convertedRating);
					mocktail.addRating(addRating);

					LOGGER.info("updating mocktail data to reference table");
					Mocktail updatedMocktail = mocktailService.addMocktail(mocktail);
					response = new ResponseEntity<>(mocktailService.getJsonString(updatedMocktail),
							HttpStatus.NO_CONTENT);
				} else {
					LOGGER.info("User not found with id: " + userId);
					message.put("message", "User does not exist");
					response = new ResponseEntity<>(message.toString(), HttpStatus.NO_CONTENT);
				}
			} else {
				LOGGER.info("Mocktail not found with id: " + mocktailId);
				message.put("message", "Mocktail does not exist");
				response = new ResponseEntity<>(message.toString(), HttpStatus.NO_CONTENT);
			}
		} else {
			message.put("message", "rating can not be empty");
			response = new ResponseEntity<>(message.toString(), HttpStatus.NO_CONTENT);
		}

		return response;
	}
}
