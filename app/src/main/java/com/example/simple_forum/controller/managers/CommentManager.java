package com.example.simple_forum.controller.managers;

import java.util.ArrayList;

import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.controller.validator.Validation;
import com.example.simple_forum.controller.validator.comment_validate;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

public class CommentManager implements BaseManager, FilterManager{

    private static ArrayList<Comment> commentList = new ArrayList<>();
    private static ICommentPersistence cp;

    // If stub
    private boolean use_stub = false;

    public CommentManager(){ use_stub = true; }

    public CommentManager(boolean use_local){

        // Use HTTP/API based persistence
        cp = PersistenceManager.get_comment_persistence(use_local);

        // Update list
        commentList = cp.get_all();
    }

    // Filter comment query set by discussion title
    // Blank string implies all
    @Override
    public ArrayList filter(String title) {

        ArrayList<Comment> querySet = new ArrayList<>();

        // If blank string, filter for all
        if (title.equals("")) {
            querySet = commentList;
        } else {

            // Get any comment items from commentList that are a part of discussion_title
            // and add them to the query set
            for(int i = 0; i < commentList.size(); i++){
                Comment c = commentList.get(i);
                Discussion c_discussion = c.getDiscussion();

                if(c_discussion != null && c_discussion.getTitle().equals(title) && !querySet.contains(c)){
                    querySet.add(c);
                }
            }
        }

        return querySet;
    }
    @Override
    public void add(Object item) {

        Comment c = (Comment) item;

        // validation
        Validation comment_val = new comment_validate(c);
        if(comment_val.validate()) {

            if(use_stub) {
                commentList.add(c);
                c.setId(commentList.size());
            } else {
                cp.insert_comment(c);
                commentList = cp.get_all();
            }
        }
    }

    @Override
    public Object get(int pos) {
        return commentList.get(pos);
    }

    @Override
    // Get by object
    public Object get(Object item) {

        int index = commentList.indexOf((Comment) item);
        Comment c = null;

        if(index != -1){
            c = commentList.get(index);
        }
        return c;
    }

    @Override
    public Object get_id(int id) {

        for(Comment c : commentList){
            if(c.getId() == id){
                return c;
            }
        }

        return null;
    }

    //returns the number of comments
    public int size(){
        return commentList.size();
    }

    @Override
    public ArrayList get_list() {
        return commentList;
    }

    @Override
    public Boolean exists(String text) {

        // Iterate through array list
        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getDiscussion().getTitle().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        commentList.clear();
    }
}