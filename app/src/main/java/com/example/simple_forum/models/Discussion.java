package com.example.simple_forum.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Discussion {
    private String title;
    private String content;
    private User user;
    private ZonedDateTime date_created;
    private ArrayList comments;

    //default constructor
    public Discussion() {
        this.title = "";
        this.content = "";
        this.user = null;
        this.date_created = null;
        this.comments = new ArrayList<Comment>();
    }

    // custom constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Discussion(String title, String content, User user, String date, Comment comment) {
        this.title = title;
        this.content = content;
        this.user = user;

        this.date_created = ZonedDateTime.parse(date);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss a");

        this.comments = new ArrayList<Comment>();
    }

    public void add_comment(Comment new_c) {
        comments.add(new_c);
    }

    /*---SETTERS---*/
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate_created(ZonedDateTime date_created) {
        this.date_created = date_created;
    }

    public void setComment(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /*---GETTERS---*/
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public ZonedDateTime getDate_created() {
        return date_created;
    }

    public ArrayList getComments() {
        return comments;
    }
}