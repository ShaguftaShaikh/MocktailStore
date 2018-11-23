package com.api.mocktailstore.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Mocktail;
import com.api.mocktailstore.entities.OrderMocktail;
import com.api.mocktailstore.entities.OrderMocktailId;
import com.api.mocktailstore.entities.PartyOrder;
import com.api.mocktailstore.repository.OrderMocktailRepository;
import com.api.mocktailstore.repository.PartyOrderRepository;

@Service
public class PartyOrderService {

	@Autowired
	private PartyOrderRepository partyOrderRepository;

	@Autowired
	private OrderMocktailRepository orderMocktailRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(PartyOrderService.class);

	private static final short ORDER_STATUS = 0;

	public synchronized long requestOrder(PartyOrder partyOrder) {
		long lastOrderedId = 0L;
		PartyOrder newOrder = new PartyOrder(partyOrder.getDescription(), partyOrder.getVenue(),
				partyOrder.getPlacedDate(), partyOrder.getDeliveryDate(), partyOrder.getOrderMocktails(),
				partyOrder.getPlacedBy(), ORDER_STATUS);
		LOGGER.debug("Saving party order: " + partyOrder.toString());
		List<OrderMocktail> orderedMocktails = partyOrder.getOrderMocktails();
		partyOrderRepository.save(newOrder);
		lastOrderedId = partyOrderRepository.getLastPartyOrderId();
		LOGGER.debug("Saved with id: " + lastOrderedId);
		for (OrderMocktail orderMocktail : orderedMocktails) {
			Mocktail mocktail = orderMocktail.getMocktail();
			final int quantity = orderMocktail.getQuantity();

			OrderMocktailId orderMocktailId = new OrderMocktailId();
			orderMocktailId.setMocktailId(mocktail.getMocktailId());
			orderMocktailId.setOrderId(lastOrderedId);

			OrderMocktail newOrderMocktail = new OrderMocktail();
			newOrderMocktail.setOrderMocktailId(orderMocktailId);
			newOrderMocktail.setQuantity(quantity);
			newOrderMocktail.setOrder(newOrder);
			newOrderMocktail.setMocktail(mocktail);

			LOGGER.debug("Saving order mocktail: " + newOrderMocktail.toString());
			orderMocktailRepository.save(newOrderMocktail);
		}
		return lastOrderedId;
	}

	public List<PartyOrder> getAllPartyOrders() {
		return partyOrderRepository.findAll();
	}

	public List<OrderMocktail> getAllMocktailOrders() {
		return orderMocktailRepository.findAll();
	}

	public List<OrderMocktail> getMocktailOrderDetailsByOrderId(long orderId) {
		return orderMocktailRepository.getMocktailOrderDetailsByOrderId(orderId);
	}

}
