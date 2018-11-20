package com.api.mocktailstore.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 */
@Entity
public class PartyOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long orderId;

	private String description;

	public PartyOrder(String description, @Size(min = 30, message = "Minimum 30 characters required") String venue,
			@NotNull(message = "order date must not be empty") Date placedDate,
			@NotNull(message = "delivery date must not be empty") Date deliveryDate, List<OrderMocktail> orderMocktails,
			User placedBy, short status) {
		super();
		this.description = description;
		this.venue = venue;
		this.placedDate = placedDate;
		this.deliveryDate = deliveryDate;
		this.orderMocktails = orderMocktails;
		this.placedBy = placedBy;
		this.status = status;
	}

	@Size(min = 30, message = "Minimum 30 characters required")
	private String venue;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "order date must not be empty")
	private Date placedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "delivery date must not be empty")
	private Date deliveryDate;

	@OneToMany(mappedBy = "order")
	private List<OrderMocktail> orderMocktails;

	@ManyToOne
	private User placedBy;

	@JsonIgnore
	private short status;

	public PartyOrder() {

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PartyOrder [orderId=").append(orderId).append(", description=").append(description)
				.append(", venue=").append(venue).append(", placedDate=").append(placedDate).append(", deliveryDate=")
				.append(deliveryDate).append(", orderMocktails=").append(orderMocktails).append(", placedBy=")
				.append(placedBy).append("]");
		return builder.toString();
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<OrderMocktail> getOrderMocktails() {
		return orderMocktails;
	}

	public void setOrderMocktails(List<OrderMocktail> orderMocktails) {
		this.orderMocktails = orderMocktails;
	}

	public User getPlacedBy() {
		return placedBy;
	}

	public void setPlacedBy(User placedBy) {
		this.placedBy = placedBy;
	}

	public List<OrderMocktail> getMocktails() {
		return orderMocktails;
	}

	public void setMocktails(List<OrderMocktail> orderMocktails) {
		this.orderMocktails = orderMocktails;
	}

	public long getId() {
		return orderId;
	}

	public void setId(long orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
