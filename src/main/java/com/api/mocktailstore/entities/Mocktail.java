package com.api.mocktailstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jayesh
 */
@Entity
public class Mocktail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long mocktailId;

	@NotEmpty(message = "Name must not be empty")
	private String name;

	@NotEmpty(message = "Tag line must not be empty")
	private String tagLine;

	private String description;

	private Boolean visible;

	@OneToMany(mappedBy = "mocktail")
	private List<OrderMocktail> orderMocktail;

	@OneToMany
	private List<Rating> ratings;

	private String imageUrl;

	@OneToMany(mappedBy = "mocktail")
	private List<IngredientMocktail> ingridientMocktail;

	private String recipe;

	public List<IngredientMocktail> getIngridientMocktail() {
		return ingridientMocktail;
	}

	public void setIngridientMocktail(List<IngredientMocktail> ingridientMocktail) {
		this.ingridientMocktail = ingridientMocktail;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagLine() {
		return tagLine;
	}

	public List<OrderMocktail> getOrderMocktail() {
		return orderMocktail;
	}

	public void setOrderMocktail(List<OrderMocktail> orderMocktail) {
		this.orderMocktail = orderMocktail;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public long getMocktailId() {
		return mocktailId;
	}

	public void setMocktailId(long mocktailId) {
		this.mocktailId = mocktailId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getVisible() {
		return visible;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

}
