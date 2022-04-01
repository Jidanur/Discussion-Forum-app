package com.example.simple_forum.controller.managers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.controller.validator.Validation;
import com.example.simple_forum.controller.validator.comment_validate;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CommentManager implements BaseManager, FilterManager{

    private static ArrayList<Comment> commentList = new ArrayList<>();

    public CommentManager(){
    }

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String fileName, Context context){

        JSONArray comments = JSONParser.get_json(context,fileName);

        for(int i = 0; i < comments.length(); i++){
            try{

                // Get json object
                JSONObject curr_comment = comments.getJSONObject(i);

                // Get the discussion object
                DiscussionManager d_manager = new DiscussionManager();
                Discussion d = (Discussion) d_manager.get(curr_comment.get("discussion").toString());

                // TODO
                // Get the user

                Comment newComment = new Comment(d, curr_comment.get("content").toString(), new User(), curr_comment.get("date_created").toString());

                commentList.add(newComment);


            } catch (JSONException e){
                Log.i("Comment_list_error", e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void add_json_str(String data) {
        JSONArray comments = JSONParser.get_json(data);

        // Iterate through serialized objects and create disc models
        Comment c = null;

        for(int i = 0; i < comments.length(); i++) {
            try {

                // Get json object
                JSONObject comm = comments.getJSONObject(i);

                // Get the discussion object
//                DiscussionManager d_manager = new DiscussionManager();
                Discussion d = new Discussion();
                d.setTitle(comm.get("discussion").toString());

                // Create comm model
                c = new Comment(d, comm.get("content").toString(), new User(), comm.get("date_created").toString());

                // Add to the list
                if (c != null) {
                    add(c);
                }

            } catch (JSONException e) {
                Log.i("DISCUSSION_LIST", e.getMessage());
            }
        }
    }

    // get commentList of a specific user
    public ArrayList<Comment> get(User user){

        ArrayList<Comment> userCommentList = new ArrayList<>();

        for(int i = 0; i < commentList.size(); i++){

            Comment curr = commentList.get(i);
            if(curr.getUser() == user){
                userCommentList.add(curr);
            }
        }

        return userCommentList;

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

                Log.i("COMM_MANAGER", "discussion_comment: " + c_discussion.getTitle());

                if(c_discussion != null && c_discussion.getTitle().equals(title) && !querySet.contains(c)){
                    querySet.add(c);
                    Log.i("COMM_MANAGER", "Comment added to query set: " + c_discussion.getTitle());
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
            commentList.add(c);
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