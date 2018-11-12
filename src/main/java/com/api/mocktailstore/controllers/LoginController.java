package com.api.mocktailstore.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.utils.MocktailStoreConstants;

@RestController
public class LoginController {

	@RequestMapping(method = RequestMethod.GET, value = MocktailStoreConstants.API_URL + "/authenticate")
	public void authenticateUser() {
		System.out.println("Authenticate");
	}
}
