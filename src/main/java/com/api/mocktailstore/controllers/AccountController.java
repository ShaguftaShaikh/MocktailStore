package com.api.mocktailstore.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.json.JSONException;
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

import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	/**
	 * Signup functionality
	 * 
	 * @param user
	 * @return response entity with valid HTTP code and message.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/signup", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> signup(@Valid @RequestBody User user) {
		LOGGER.info("Recieved request for signup");
		ResponseEntity<String> response = null;
		boolean userExist = userExist(user.getEmail());
		if (userExist) {
			LOGGER.info("email id exist");
			JSONObject exceptionJson = new JSONObject();
			exceptionJson.put("message", "email id already exists");
			response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.BAD_REQUEST);
		} else {
			User savedUser = userService.saveUser(user);
			JSONObject responseObject = new JSONObject(savedUser);
			response = new ResponseEntity<>(responseObject.toString(), HttpStatus.OK);
			LOGGER.info("signup successful");
		}
		return response;
	}

	/**
	 * Check whether email id already exist or not
	 * 
	 * @param email
	 * @return true or false
	 */
	public boolean userExist(String email) {
		LOGGER.info("Checking for email existence");
		User user = userService.findByEmail(email);
		if (user != null) {
			LOGGER.info("email id exist");
			return true;
		}
		LOGGER.info("email id not found");
		return false;
	}

	/**
	 * 
	 * Login through email and password
	 * 
	 * @param user
	 *            for email and password
	 * @return response entity with valid HTTP code and message.
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> login(@RequestBody User user) {
		LOGGER.info("Received a request for login");
		ResponseEntity<String> response = null;
		try {
			if (!Objects.isNull(user) && !Objects.isNull(user.getEmail()) && !Objects.isNull(user.getPassword())) {
				final String username = user.getEmail();
				final User savedUser = userService.findByEmail(username);
				JSONObject exceptionJson = new JSONObject();
				exceptionJson.put("message", "Invalid login. Please check your name and password.");
				if (Objects.isNull(savedUser)) {
					LOGGER.info("Login failed.");
					response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.UNAUTHORIZED);
				} else {
					final boolean isLoginSuccessful = userService.login(savedUser, user);
					if (isLoginSuccessful) {
						JSONObject userJson = new JSONObject();
						userJson.put("id", savedUser.getId()).put("firstName", savedUser.getFirstName())
								.put("lastName", savedUser.getLastName()).put("contactNo", savedUser.getContactNo())
								.put("email", savedUser.getEmail());
						response = new ResponseEntity<>(userJson.toString(), HttpStatus.OK);
						LOGGER.info("Login successful");
					} else {
						response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.UNAUTHORIZED);
						LOGGER.info("Login failed.");
					}
				}
			} else {
				JSONObject exceptionJson = new JSONObject();
				exceptionJson.put("message", "Email or password cannot be null.");
				response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.BAD_REQUEST);
				LOGGER.info("Login failed.");
			}
		} catch (JSONException e) {
			LOGGER.error("Error occurred while creating JSON object");
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/**
	 * Edits user profile except email
	 * 
	 * @param user
	 *            contains updated values
	 * @return user with updated values
	 */
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public ResponseEntity<User> editProfile(@RequestBody User user) {
		LOGGER.info("Received a request for edit profile");
		ResponseEntity<User> response = null;
		final User updatedUser = userService.editProfile(user);
		response = new ResponseEntity<>(updatedUser, HttpStatus.OK);
		return response;
	}

}
