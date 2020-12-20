package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokenmodel")
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "token")
    private String tokenUsuario;

    @Column(name = "dateCreated")
    private Date dateCreated;

    public String getTokenUsuario() {
        return tokenUsuario;
    }

    public void setTokenUsuario(String tokenUsuario) {
        this.tokenUsuario = tokenUsuario;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
