package com.api.mocktailstore.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.modals.Mocktails;
import com.api.mocktailstore.service.MocktailService;

@CrossOrigin
@RestController
@RequestMapping("/mocktail")
public class MocktailController {

	@Autowired
	MocktailService mocktailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MocktailController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public List<Mocktails> getMocktails() {
		LOGGER.info("Fetching list of mocktails");
		return mocktailService.getMocktails();
	}
}
