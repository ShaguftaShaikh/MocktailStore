package com.api.mocktailstore.modals;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author jayesh
 */
@Entity
public class Visitor extends User {

	private static final long serialVersionUID = 1L;

	@Size(min = 10, max = 10, message = "contact number should be 10 digits long")
	private String contactNo;
	
	@Email(message="Invalid email address")
	private String email;

	@OneToMany(mappedBy = "visitorId")
	private List<Orders> orders;

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> order) {
		this.orders = order;
	}

}
