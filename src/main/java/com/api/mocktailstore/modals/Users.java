package com.api.mocktailstore.modals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jayesh
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users implements Serializable {

    @Id
    @GeneratedValue(
    strategy= GenerationType.AUTO, 
    generator="native"
)
@GenericGenerator(
    name = "native", 
    strategy = "native"
)    
    protected Integer id;
    protected String username;
    protected String password;
    protected String fname;
    protected String lname;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    
}
