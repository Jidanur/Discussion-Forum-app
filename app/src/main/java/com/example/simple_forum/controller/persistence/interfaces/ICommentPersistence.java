package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Comment;

import java.util.ArrayList;

public interface ICommentPersistence {

    // Insert a discussion into the database
    public void insert_comment(Comment c);

    // Delete a discussion from the db
    public void delete_comment(Comment c);

    // Get all topics in the DB
    public ArrayList<Comment> get_all();
}
