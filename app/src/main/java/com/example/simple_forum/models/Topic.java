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
        this.discussions = new ArrayList<Discussion>();

        this.set_date(date);
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

    // takes a string and converts it to SimpleDateFormat
    public void set_date(String date){
        //"2022-02-28T00:52:48.769746Z"
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String time = "";
        int i = 0;
        while (i < date.length()) {

            if (i == 10) {
                time += " ";
            }
            else {
                time += date.charAt(i);
            }

            i++;
        }
        try {
            this.date_created = dtf.parse(time);
        } catch (ParseException e){
            System.out.println("date error");
        }
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

