package com.api.mocktailstore.controllers;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.modals.Visitor;

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
	public ResponseEntity<String> login(@RequestBody Visitor visitor) {
		ResponseEntity<String> response = null;
		try {
			if (!Objects.isNull(visitor) && !Objects.isNull(visitor.getUsername())
					&& !Objects.isNull(visitor.getPassword())) {
				String username = visitor.getUsername();

			}
		} catch (Exception e) {

		}
		return response;
	}

}
