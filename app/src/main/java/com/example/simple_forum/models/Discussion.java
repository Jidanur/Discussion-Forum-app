package com.example.simple_forum.models;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Discussion {
    private Topic topic;
    private String title;
    private String content;
    private User user;
    private Date date_created;
    private ArrayList comments;

    //default constructor
    public Discussion() {
        this.topic = null;
        this.title = "";
        this.content = "";
        this.user = null;
        this.date_created = null;
        this.comments = new ArrayList<Comment>();
    }

    // custom constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Discussion(Topic topic, String title, String content, User user, String date, Comment comment) {
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.user = user;

        SimpleDateFormat dtf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss a");

        try {
            this.date_created = dtf.parse(date);
        } catch (ParseException e){
            Log.i("TOPIC_MODEL", e.getMessage());
        }

        this.comments = new ArrayList<Comment>();
    }

    public void add_comment(Comment new_c) {
        comments.add(new_c);
    }

    /*---SETTERS---*/
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public void setComment(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /*---GETTERS---*/
    public Topic getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Date getDate_created() {
        return date_created;
    }

    public ArrayList getComments() {
        return comments;
    }
}