package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Discussion;

import java.util.ArrayList;

public interface IDiscussionPersistence {

    // Insert a discussion into the database
    public void insert_disc(Discussion d);

    // Delete a discussion from the db
    public void delete_disc(Discussion d);

    // Get a certain discussion
    public Discussion get(String title);

    // Get all discussions in the DB
    public ArrayList<Discussion> get_all();

    // Get the count of all the rows
    public int get_count();
}
