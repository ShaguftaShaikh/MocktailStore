package com.api.mocktailstore.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IngredientMocktailId implements Serializable {

	private static final long serialVersionUID = 1L;
	private long mocktailId;
	private long ingredientId;

	public long getMocktailId() {
		return mocktailId;
	}

	public void setMocktailId(long mocktailId) {
		this.mocktailId = mocktailId;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

}
