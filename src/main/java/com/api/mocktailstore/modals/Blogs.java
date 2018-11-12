/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.mocktailstore.modals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Blogs implements Serializable {
   
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
    private String title;
    private String subtitle;
    private String content;
    private String venue;
    private String visibile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getVisibile() {
        return visibile;
    }

    public void setVisibile(String visibile) {
        this.visibile = visibile;
    }
    
        
}
