package com.example.simple_forum.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Topic {
    private String title;
    private User user;
    private Date date_created;
    private ArrayList discussions;

    // Default constructor
    public Topic() {
        this.title = "";
        this.user = null;
        this.date_created = null;
        this.discussions = new ArrayList<Discussion>();
    }

    // Custom constructor
    public Topic (String title, User user, String date){
        this.title = title;
        this.user = user;
        SimpleDateFormat dtf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss a");
        this.discussions = new ArrayList<Discussion>();

        try {
            this.date_created = dtf.parse(date);
        } catch (ParseException e){
            System.out.println("date error");
        }
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

    public void setDate_created(Date date_created) {
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

    public Date getDate_created() {
        return date_created;
    }

    public ArrayList<Discussion> getDiscussions() {
        return discussions;
    }
}

