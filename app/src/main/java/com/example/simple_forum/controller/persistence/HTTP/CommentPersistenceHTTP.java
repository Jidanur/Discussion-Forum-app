package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.models.Comment;

import java.util.ArrayList;

public class CommentPersistenceHTTP implements ICommentPersistence{

    @Override
    public void insert_comment(Comment c) {
    }

    @Override
    public Comment get(String comment) {
        return null;
    }

    @Override
    public Comment get(String content) {
        return null;
    }

    @Override
    public void delete_comment(Comment c) {

    }

    @Override
    public ArrayList<Comment> get_all() {
        return null;
    }

    @Override
    public int get_count() {
        return 0;
    }
}
