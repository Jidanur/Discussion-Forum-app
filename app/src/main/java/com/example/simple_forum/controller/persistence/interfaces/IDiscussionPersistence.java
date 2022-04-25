package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Discussion;

import java.util.ArrayList;

public interface IDiscussionPersistence {

    // Insert a discussion into the database
    void insert_disc(Discussion d);

    // Delete a discussion from the db
    void delete_disc(Discussion d);

    // Get a certain discussion
    Discussion get(String title);
    Discussion get(int id);

    // Get all discussions in the DB
    ArrayList<Discussion> get_all();

    // Get the count of all the rows
    int get_count();
}
