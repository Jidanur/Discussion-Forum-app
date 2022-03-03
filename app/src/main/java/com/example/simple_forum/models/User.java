package com.example.simple_forum.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.time.format.DateTimeFormatter;

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
        this.id = 0;
        this.date_created = null;
    }

    // Custom constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String username, String password, String email, String bio, String date){
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.token = "";
        this.id = 0;

        this.date_created = ZonedDateTime.parse(date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss a");
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
