package com.example.simple_forum.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Comment {
    private String content;
    private User user;
    private Date date_created;

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

        SimpleDateFormat dtf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss a");

        try {
            this.date_created = dtf.parse(date);
        } catch (ParseException e){
            Log.i("TOPIC_MODEL", e.getMessage());
        }
    }

    /*---SETTERS---*/
    public void set_content(String content){
        this.content = content;
    }

    public void set_user(User user){
        this.user = user;
    }

    public void set_date(Date date){
        this.date_created = date;
    }

    /*---GETTERS---*/
    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date_created;
    }
}
