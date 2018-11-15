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

import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	UserService visitorService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	public void signup(@Valid @RequestBody User user) {
		LOGGER.info("signup method is called");
		visitorService.saveVisitor(user);
	}

	/*@RequestMapping(method = RequestMethod.POST, value = "/username/exist")
	public JsonNode usernameExist(@RequestBody String username) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode responseNode = mapper.createObjectNode();

		if (username != "" && username != null && !username.equals("")) {
			Customer visitor = visitorService.findByUsername(username);
			if (visitor != null) {
				((ObjectNode) responseNode).put("result", true);
			} else {
				((ObjectNode) responseNode).put("result", false);
			}
		} else {
			((ObjectNode) responseNode).put("result", true);
		}
		return responseNode;
	}*/

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public void login() {
		LOGGER.info("login method is called");
	}

}
