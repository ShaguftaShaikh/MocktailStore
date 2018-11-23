package com.api.mocktailstore.controllers;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.Rating;
import com.api.mocktailstore.service.MocktailService;
import com.api.mocktailstore.service.RatingService;

@CrossOrigin
@RestController
@RequestMapping("/mocktail")
public class MocktailController {

	@Autowired
	private MocktailService mocktailService;

	@Autowired
	private RatingService ratingService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MocktailController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getMocktails() {
		LOGGER.info("Recieved request for fetching list of mocktails");
		List<Mocktail> mocktails = mocktailService.getMocktails();
		JSONArray responseArray = new JSONArray();
		for (Mocktail mocktail : mocktails) {
			List<Rating> ratings = ratingService.getRatingsByMocktailId(mocktail);
			String response = mocktailService.getJsonString(mocktail);
			try {
				JSONObject jsonObj = new JSONObject(response);
				JSONArray ratingArray = new JSONArray();
				for (Rating r : ratings) {
					String ratingResponse = ratingService.getJsonString(r);
					JSONObject obj = new JSONObject(ratingResponse);
					obj.remove("ratedFor");
					ratingArray.put(obj);
				}
				jsonObj.put("ratings", ratingArray);
				responseArray.put(jsonObj);
			} catch (Exception e) {
				LOGGER.error("Error in parsing json object " + e.getMessage());
			}

		}
		System.out.println("JSONArray response: " + responseArray.toString());
		return responseArray.toString();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mocktail addMocktail(@RequestBody Mocktail mocktail) {
		LOGGER.info("Received a request to add mocktail" + mocktail);
		return mocktailService.addMocktail(mocktail);
	}

	public Mocktail updateMocktail(@RequestBody Mocktail mocktail) {
		return null;
	}

}
