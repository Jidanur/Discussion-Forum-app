package com.example.simple_forum.models;

public class Comment {
    private String content;
    private User user;
    private String date;

    // Default constructor
    public Comment(){
        this.content = "";
        this.user = null;
        this.date = null;
    }

    // custom constructor
    public Comment(String content, User user, String date){
        this.content = content;
        this.user = user;
        this.date = date;
    }

    /*---SETTERS---*/
    public void set_content(String content){
        this.content = content;
    }

    public void set_user(User user){
        this.user = user;
    }

    public void set_date(String date){
        this.date = date;
    }

    /*---GETTERS---*/
    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }
}
