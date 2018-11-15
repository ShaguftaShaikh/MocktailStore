package com.api.mocktailstore.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderMocktailId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long orderId;
	private long mocktailId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getMocktailId() {
		return mocktailId;
	}

	public void setMocktailId(long mocktailId) {
		this.mocktailId = mocktailId;
	}
}
