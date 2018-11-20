package com.api.mocktailstore.controllers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.mocktailstore.entities.PartyOrder;
import com.api.mocktailstore.service.PartyOrderService;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private PartyOrderService partyOrderService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@RequestMapping(method = RequestMethod.POST, value = "/request")
	public ResponseEntity<String> requestOrder(@RequestBody PartyOrder partyOrder) {
		ResponseEntity<String> response = null;
		LOGGER.info("Received a request to save party order: " + partyOrder.toString());
		long orderId = partyOrderService.requestOrder(partyOrder);
		JSONObject jsonResponse = new JSONObject();
		if (orderId > 0) {
			jsonResponse.put("message", "success");
			response = new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
		} else {
			jsonResponse.put("message", "fail");
			response = new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
