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

import com.api.mocktailstore.modals.Visitor;
import com.api.mocktailstore.service.VisitorService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	VisitorService visitorService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public void signup(@Valid @RequestBody Visitor visitor) {
		LOGGER.info("signup method is called");
		visitorService.saveVisitor(visitor);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/username/exist")
	public JsonNode usernameExist(@RequestBody String username) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode responseNode = mapper.createObjectNode();

		if (username != "" && username != null && !username.equals("")) {
			Visitor visitor = visitorService.findByUsername(username);
			if (visitor != null) {
				((ObjectNode) responseNode).put("result", false);
			} else {
				((ObjectNode) responseNode).put("result", true);
			}
		} else {
			((ObjectNode) responseNode).put("result", false);
		}
		return responseNode;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> login(@RequestBody Visitor visitor) {
		ResponseEntity<String> response = null;
		try {
			if (!Objects.isNull(visitor) && !Objects.isNull(visitor.getUsername())
					&& !Objects.isNull(visitor.getPassword())) {
				String username = visitor.getUsername();
				Visitor savedVisitor = visitorService.findByUsername(username);
				if (Objects.isNull(savedVisitor)) {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				} else {
					boolean isLoginSuccessful = visitorService.login(savedVisitor, visitor);
					if (isLoginSuccessful) {
						JSONObject userJson = new JSONObject();
						userJson.put("email", visitor.getUsername()).put("password", visitor.getPassword());
						response = new ResponseEntity<>(userJson.toString(), HttpStatus.OK);
					} else {
						JSONObject exceptionJson = new JSONObject();
						exceptionJson.put("message", "Invalid login. Please check your name and password.");
						response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.UNAUTHORIZED);
					}
				}
			} else {
				JSONObject exceptionJson = new JSONObject();
				exceptionJson.put("message", "Email or password cannot be null.");
				response = new ResponseEntity<>(exceptionJson.toString(), HttpStatus.BAD_REQUEST);
			}
		} catch (JSONException e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
