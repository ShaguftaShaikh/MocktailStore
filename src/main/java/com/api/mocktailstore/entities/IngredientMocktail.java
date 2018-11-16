package com.api.mocktailstore.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class IngredientMocktail implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientMocktailId ingredientMocktailId;

	@ManyToOne
	@MapsId("ingredientId")
	private Ingredient ingredient;

	@ManyToOne
	@MapsId("mocktailId")
	private Mocktail mocktail;

	public IngredientMocktailId getIngredientMocktailId() {
		return ingredientMocktailId;
	}

	public void setIngredientMocktailId(IngredientMocktailId ingredientMocktailId) {
		this.ingredientMocktailId = ingredientMocktailId;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Mocktail getMocktail() {
		return mocktail;
	}

	public void setMocktail(Mocktail mocktail) {
		this.mocktail = mocktail;
	}

}
