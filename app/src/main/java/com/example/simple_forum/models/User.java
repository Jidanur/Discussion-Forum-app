package com.example.simple_forum.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String bio;
    private String token;
    private int id;

    // Default constructor
    public User(){
        this.username = "";
        this.password = "";
        this.email = "";
        this.bio = "";
        this.token = "";
        this.id = 0;
    }

    // Custom constructor
    public User(String username, String password, String email, String bio){
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.token = "";
        this.id = 0;
    }

    public User(int id, String username, String password, String email, String bio){
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.token = "";
        this.id = id;
    }

    /*---SETTERS---*/
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setToken(String token) {
        this.token = token;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(User u){

        return username.equals(u.getUsername()) && password.equals(u.getPassword())
        && bio.equals(u.getBio()) && email.equals(u.getEmail());
    }

    public JSONObject serialize(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("username", username);
            obj.put("password", password);
            obj.put("email", email);
            obj.put("bio", bio);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
