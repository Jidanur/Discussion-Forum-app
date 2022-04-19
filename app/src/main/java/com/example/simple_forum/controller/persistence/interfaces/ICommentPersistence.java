package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Comment;

import java.util.ArrayList;

public interface ICommentPersistence {

    // Insert a discussion into the database
    void insert_comment(Comment c);

    // Get a comment from the database
    Comment get(String comment);

    // Delete a discussion from the db
    void delete_comment(Comment c);

    // Get all topics in the DB
    ArrayList<Comment> get_all();

    // Get the count of all the rows
    int get_count();
}
