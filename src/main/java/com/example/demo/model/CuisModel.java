package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cuis")
public class CuisModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "cuis")
    private String cuis;

    @Column(name = "dateCreated")
    private Date dateCreated;

    public String getCuis() {
        return cuis;
    }

    public void setCuis(String tokenUsuario) {
        this.cuis = tokenUsuario;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
