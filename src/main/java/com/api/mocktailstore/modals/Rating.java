package com.api.mocktailstore.modals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jayesh
 */
@Entity
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Mocktails mocktailId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;

	private Integer star;
	private String comment;
	private boolean Visibility;

	public Mocktails getMocktailId() {
		return mocktailId;
	}

	public void setMocktailId(Mocktails mocktailId) {
		this.mocktailId = mocktailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isVisibility() {
		return Visibility;
	}

	public void setVisibility(boolean Visibility) {
		this.Visibility = Visibility;
	}
}
