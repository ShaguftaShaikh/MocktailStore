package com.api.mocktailstore.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
				((ObjectNode) responseNode).put("result", true);
			} else {
				((ObjectNode) responseNode).put("result", false);
			}
		} else {
			((ObjectNode) responseNode).put("result", true);
		}
		return responseNode;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public void login() {
		LOGGER.info("login method is called");
	}

}
