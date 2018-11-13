package com.api.mocktailstore.modals;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jayesh
 */
@Entity
public class Mocktails implements Serializable {

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
    private String name;
    private String tagLine;
    private String description;
    private String flavour;
    private Boolean visible;
    
    @OneToMany(mappedBy = "mocktailId")
    private List<Rating> rating;

    @OneToMany
    private List<MocktailImages> images;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public List<Rating> getRating() {
        return rating;
    }
    public List<MocktailImages> getImages() {
        return images;
    }

    public void setImages(List<MocktailImages> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Mocktails{" + "id=" + id + ", name=" + name + ", tagLine=" + tagLine + ", description=" + description + ", flavour=" + flavour + ", visible=" + visible + ", \nrating=" + rating + ", \nimages=" + images + '}';
    }
    
    
    
}
