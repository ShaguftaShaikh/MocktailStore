
package com.api.mocktailstore.modals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jayesh
 */
@Entity
public class MocktailImages implements Serializable {
   
    @Id
    @GeneratedValue(
    strategy= GenerationType.AUTO, 
    generator="native"
    )
    @GenericGenerator(
        name = "native", 
        strategy = "native"
    )
    private Integer id;
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}
