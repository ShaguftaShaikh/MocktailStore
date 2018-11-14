package com.api.mocktailstore.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.modals.Visitor;
import com.api.mocktailstore.service.VisitorService;
import com.api.mocktailstore.utils.MocktailStoreConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Shagufta
 *
 */
@CrossOrigin
@RestController
public class SignupController {

	@Autowired
	VisitorService visitorService;

	@RequestMapping(method = RequestMethod.POST, value = MocktailStoreConstants.API_URL + "/signup")
	public void signupUser(@Valid @RequestBody Visitor visitor) {
		visitorService.saveVisitor(visitor);
	}

	@RequestMapping(method = RequestMethod.POST, value = MocktailStoreConstants.API_URL + "/username/exist")
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
}
