package com.micromata.webengineering.demo.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Luan Hajzeraj on 26.05.2017.
 */

//@Entity-Annotation sorgt für die persistierung des Users in der DB. Muss "User_" sein, da "User" in Heroku reserviert
@Entity(name= "User_")
public class User {

    //Generiert eine unique ID
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}