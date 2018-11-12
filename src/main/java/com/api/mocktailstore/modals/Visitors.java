package com.api.mocktailstore.modals;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jayesh
 */
@Entity
public class Visitors extends Users{
    
    private Integer contactNo;
    private String email;
    
    @OneToMany(mappedBy = "visitorId")
    private List<Orders> orders;

    public Integer getContactNo() {
        return contactNo;
    }

    public void setContactNo(Integer contactNo) {
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
