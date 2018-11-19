package com.api.mocktailstore.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author jayesh
 */
@Entity
// @NamedQuery(name = "Mocktail.findAll", query = "SELECT m.mocktailId,
// im.ingredient.ingredientId FROM Mocktail m, IngredientMocktail im where
// m.mocktailId=:im.mocktail.mocktailId")
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

	@OneToMany(mappedBy = "mocktail", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<OrderMocktail> orderMocktail;

	@OneToMany
	private List<Rating> ratings;

	private String imageUrl;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "mocktail_ingredient", joinColumns = @JoinColumn(name = "mocktailId"), inverseJoinColumns = @JoinColumn(name = "ingredientId"))
	private List<Ingredient> ingredients;

	private String recipe;

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getName() {
		return name;
	}

	public Mocktail() {

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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
