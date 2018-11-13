package com.api.mocktailstore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public void signup() {
		System.out.println("signup method is called");
		LOGGER.info("signup method is called");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public void login() {
		System.out.println("login method is called");
		LOGGER.info("login method is called");
	}

}
