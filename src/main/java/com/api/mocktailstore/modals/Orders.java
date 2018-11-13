package com.api.mocktailstore.modals;

import java.io.Serializable;
import java.sql.Date;
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
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer id;
	private String description;
	private String venue;
	private Date OrderDate;

	@ManyToOne
	private Visitor visitorId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date OrderDate) {
		this.OrderDate = OrderDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Visitor getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Visitor visitorId) {
		this.visitorId = visitorId;
	}
}
