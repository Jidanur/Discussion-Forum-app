package com.example.simple_forum.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

public class Discussion {
    private String title;
    private String content;
    private User user;
    private LocalDateTime date_created;
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
    public Discussion(String title, String content, User user, LocalDateTime date, Comment comment) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.date_created = date;
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

    public void setDate_created(LocalDateTime date_created) {
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

    public LocalDateTime getDate_created() {
        return date_created;
    }

    public ArrayList getComments() {
        return comments;
    }
}
