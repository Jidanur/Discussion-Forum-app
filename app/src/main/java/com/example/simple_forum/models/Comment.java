package com.example.simple_forum.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private Discussion discussion;
    private String content;
    private User user;
    private Date date_created;
    private int id;

    // Default constructor
    public Comment(){
        this.discussion = null;
        this.content = "";
        this.user = null;
        this.date_created = null;
        this.id = 0;
    }

    // custom constructor
    public Comment(Discussion discussion, String content, User user, String date){
        this.discussion = discussion;
        this.content = content;
        this.user = user;
        this.id = 0;

       this.set_date(date);
    }

    public Comment(int id, Discussion discussion, String content, User user, String date){
        this.discussion = discussion;
        this.content = content;
        this.user = user;
        this.id = id;

        this.set_date(date);
    }

    /*---SETTERS---*/
    public void set_Discussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public void set_content(String content){
        this.content = content;
    }

    public void set_user(User user){
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

            if (date.charAt(i) == 'T') {
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
            System.out.println("date error" + date);
        }
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

    public String getDate() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date_created != null ? dtf.format(date_created) : "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject serialize(){

        JSONObject obj = new JSONObject();
        try {
            obj.put("discussion", discussion.getId());
            obj.put("content", content);
            obj.put("user", user.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
