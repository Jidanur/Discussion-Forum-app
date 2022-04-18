package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.persistence.HSQLDB.CommentPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.interfaces.BaseHTTPInterface;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.models.Comment;

import java.util.ArrayList;

public class CommentPersistenceHTTP implements ICommentPersistence, BaseHTTPInterface{

    @Override
    public boolean check_server_status() {
        // TODO
        // Ping server
        return false;
    }

    @Override
    public void insert_comment(Comment c) {

    }

    @Override
    public void delete_comment(Comment c) {

    }

    @Override
    public ArrayList<Comment> get_all() {
        return null;
    }
}
