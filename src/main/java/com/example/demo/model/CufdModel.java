package com.example.demo.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cufd")
public class CufdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "cufd")
    private String cufd;

    @Column(name = "dateCreated")
    private Date dateCreated;

    public String getCufd() {
        return cufd;
    }

    public void setCufd(String tokenUsuario) {
        this.cufd = tokenUsuario;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
