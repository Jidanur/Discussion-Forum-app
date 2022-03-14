package com.example.simple_forum.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Comment {
    private Discussion discussion;
    private String content;
    private User user;
    private Date date_created;

    // Default constructor
    public Comment(){
        this.discussion = null;
        this.content = "";
        this.user = null;
        this.date_created = null;
    }

    // custom constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Comment(Discussion discussion, String content, User user, String date){
        this.discussion = discussion;
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
    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

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
    public Discussion getDiscussion() {
        return discussion;
    }

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
