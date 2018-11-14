package com.api.mocktailstore.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.modals.Visitor;
import com.api.mocktailstore.service.VisitorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public void signup() {
		System.out.println("signup method is called");
		LOGGER.info("signup method is called");
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		ResponseEntity<String> response = null;
		try {
			if (!Objects.isNull(user) && !Objects.isNull(user.getUsername()) && !Objects.isNull(user.getPassword())) {
				String username = user.getUsername();

			}
		} catch (Exception e) {

		}
		return response;
	}

}
