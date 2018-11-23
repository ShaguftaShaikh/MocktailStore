package com.api.mocktailstore.controllers;

import java.util.List;

import org.json.JSONArray;
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

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.OrderMocktail;
import com.api.mocktailstore.entities.PartyOrder;
import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.service.MocktailService;
import com.api.mocktailstore.service.PartyOrderService;
import com.api.mocktailstore.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private PartyOrderService partyOrderService;

	@Autowired
	private UserService userService;

	@Autowired
	private MocktailService mocktailService;

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

	@RequestMapping(method = RequestMethod.POST, value = "/getAllOrders")
	public String getAllOrders() {
		List<PartyOrder> orders = partyOrderService.getAllPartyOrders();
		JSONArray orderJsonArray = new JSONArray();
		for (PartyOrder partyOrder : orders) {
			System.out.println("partyOrder: " + partyOrder.getDescription());
			System.out.println("partyOrder: " + partyOrder.getOrderId());

			User placedBy = userService.findById(partyOrder.getPlacedBy().getId());

			JSONObject orderJson = new JSONObject();

			orderJson.put("orderId", partyOrder.getOrderId()).put("description", partyOrder.getDescription())
					.put("deliveryDate", partyOrder.getDeliveryDate()).put("placedDate", partyOrder.getPlacedDate())
					.put("placedBy", placedBy.getFirstName() + " " + placedBy.getLastName())
					.put("status", partyOrder.getStatus()).put("venue", partyOrder.getVenue());

			JSONArray mocktailJsonArray = new JSONArray();
			List<OrderMocktail> orderMocktails = partyOrderService
					.getMocktailOrderDetailsByOrderId(partyOrder.getOrderId());
			for (OrderMocktail orderMocktail : orderMocktails) {
				final int quantity = orderMocktail.getQuantity();
				final long mocktailId = orderMocktail.getMocktail().getMocktailId();
				// final long orderId = orderMocktail.getOrder().getOrderId();

				Mocktail orderedMocktail = mocktailService.getMocktailById(mocktailId);

				JSONObject mocktailJson = new JSONObject();
				mocktailJson.put("quantity", quantity);
				mocktailJson.put("mocktailName", orderedMocktail.getName());

				System.out.println("mocktailJson: " + mocktailJson.toString());
				mocktailJsonArray.put(mocktailJson);
			}
			orderJson.put("mocktails", mocktailJsonArray);
			orderJsonArray.put(orderJson);
			System.out.println("orderJson: " + orderJson.toString());

		}
		System.out.println("OrderJsonArray: " + orderJsonArray.toString());
		return orderJsonArray.toString();
	}

}
