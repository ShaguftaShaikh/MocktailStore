package com.api.mocktailstore.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class OrderMocktail {

	@EmbeddedId
	private OrderMocktailId orderMocktailId;

	@ManyToOne
	@MapsId("mocktailId")
	private Mocktail mocktail;

	@ManyToOne
	@MapsId("orderId")
	private PartyOrder order;

	private int quantity;

	public OrderMocktail() {

	}

	public Mocktail getMocktail() {
		return mocktail;
	}

	public void setMocktail(Mocktail mocktail) {
		this.mocktail = mocktail;
	}

	public OrderMocktailId getOrderMocktailId() {
		return orderMocktailId;
	}

	public void setOrderMocktailId(OrderMocktailId orderMocktailId) {
		this.orderMocktailId = orderMocktailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderMocktail [orderMocktailId=").append(orderMocktailId).append(", mocktail=").append(mocktail)
				.append(", order=").append(order).append(", quantity=").append(quantity).append("]");
		return builder.toString();
	}

}
