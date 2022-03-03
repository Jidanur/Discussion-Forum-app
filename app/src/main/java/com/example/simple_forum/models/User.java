package com.example.simple_forum.models;

import java.time.*;

public class User {
    private String username;
    private String password;
    private String email;
    private String bio;
    private String token;
    private int id;
    private ZonedDateTime date_created;

    // Default constructor
    public User(){
        this.username = "";
        this.password = "";
        this.email = "";
        this.bio = "";
        this.token = "";
        this.date_created = null;
    }

    // Custom constructor
    public User(String username, String password, String email, String bio, ZonedDateTime date){
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.token = "";
        this.date_created = date;
    }

    /*---SETTERS---*/
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDate_created(ZonedDateTime date_created) {
        this.date_created = date_created;
    }

    /*---GETTERS---*/
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getToken() {
        return token;
    }

    public ZonedDateTime getDate_created() {
        return date_created;
    }
}
