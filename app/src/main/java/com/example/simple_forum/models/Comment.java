package com.example.simple_forum.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.time.format.DateTimeFormatter;


public class Comment {
    private String content;
    private User user;
    private ZonedDateTime date_created;

    // Default constructor
    public Comment(){
        this.content = "";
        this.user = null;
        this.date_created = null;
    }

    // custom constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Comment(String content, User user, String date){
        this.content = content;
        this.user = user;

        this.date_created = ZonedDateTime.parse(date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss a");
    }

    /*---SETTERS---*/
    public void set_content(String content){
        this.content = content;
    }

    public void set_user(User user){
        this.user = user;
    }

    public void set_date(ZonedDateTime date){
        this.date_created = date;
    }

    /*---GETTERS---*/
    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public ZonedDateTime getDate() {
        return date_created;
    }
}
