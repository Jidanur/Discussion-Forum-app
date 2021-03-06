package com.example.simple_forum.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Discussion implements Serializable {
    private Topic topic;
    private String title;
    private String content;
    private User user;
    private Date date_created;
    private ArrayList<Comment> comments;
    private int id;

    //default constructor
    public Discussion() {
        this.topic = null;
        this.title = "";
        this.content = "";
        this.user = null;
        this.date_created = null;
        this.comments = new ArrayList<>();
        this.id = 0;

    }

    // custom constructor
    public Discussion(Topic topic, String title, String content, User user, String date) {
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.user = user;
        this.comments = new ArrayList<>();
        this.set_date(date);
    }

    public Discussion(int id, Topic topic, String title, String content, User user, String date) {
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.user = user;
        this.set_date(date);
        this.id = id;
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

    public String getDate() {
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  date_created == null ? "" : dtf.format(date_created);
    }

    public ArrayList getComments() {
        return comments;
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
            obj.put("topic", topic.getId());
            obj.put("title", title);
            obj.put("content", content);
            obj.put("user", user.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
