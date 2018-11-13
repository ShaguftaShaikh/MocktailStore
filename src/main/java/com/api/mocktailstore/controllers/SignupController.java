package com.api.mocktailstore.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.modals.Visitor;
import com.api.mocktailstore.utils.MocktailStoreConstants;

@RestController
public class SignupController {

	@RequestMapping(method = RequestMethod.POST, value = MocktailStoreConstants.API_URL + "/signup")
	public void signupUser(@RequestBody Visitor visitor) {
		System.out.println(visitor.getFirstName());
	}
}
