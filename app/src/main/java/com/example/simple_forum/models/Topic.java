package com.example.simple_forum.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Topic {
    private String title;
    private User user;
    private LocalDateTime date_created;
    private ArrayList discussions;

    // Default constructor
    public Topic() {
        this.title = "";
        this.user = null;
        this.date_created = null;
        this.discussions = new ArrayList<Discussion>();
    }

    // Custom constructor
    public Topic (String title, User user, LocalDateTime date){
        this.title = title;
        this.user = user;
        this.date_created = date;
        this.discussions = new ArrayList<Discussion>();
    }

    public void add_discussion(Discussion new_d) {
        discussions.add(new_d);
    }

    /*---SETTTERS---*/
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDate_created(LocalDateTime date_created) {
        this.date_created = date_created;
    }

    public void setDiscussion(ArrayList<Discussion> discussions) {
        this.discussions = discussions;
    }

    /*---GETTERS---*/
    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    public ArrayList<Discussion> getDiscussions() {
        return discussions;
    }
}

