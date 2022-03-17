package com.example.simple_forum.controller.managers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CommentManager implements BaseManager, FilterManager{

    private static ArrayList<Comment> commentList;

    public CommentManager(){

        commentList = new ArrayList<Comment>();
    }

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String fileName, Context context){

        JSONArray comments = JSONParser.get_json(context,fileName);

        for(int i = 0; i < comments.length(); i++){
            try{

                // Get json object
                JSONObject curr_comment = comments.getJSONObject(i);


                Comment newComment = new Comment();
                newComment.set_Discussion(curr_comment.get("discussion").toString());
                newComment.set_content(curr_comment.get("content").toString());
                newComment.set_user(new User());
                newComment.set_date(curr_comment.get("date_created").toString());

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
        for(int i = 0; i < comments.length(); i++) {
            try {

                // Get json object
                JSONObject comm = comments.getJSONObject(i);

                // Create comm model
                Comment c = new Comment();
                c.set_Discussion(comm.get("discussion").toString());
                c.set_content(comm.get("content").toString());
                c.set_user(new User());
                c.set_date(comm.get("date_created").toString());

                // Add to the list
                add(c);

            } catch (JSONException e) {
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }
    }

    // get commentList of a specific user
    public ArrayList<Comment> get(User user){

        ArrayList<Comment> userCommentList = new ArrayList<Comment>();

        for(int i =0;i<commentList.size();i++){

            Comment curr = commentList.get(i);
            if(curr.getUser() == user){
                userCommentList.add(curr);
            }
        }

        return userCommentList;

    }

    // Filter comment queryset by discussion title
    // Blank string implies all
    @Override
    public ArrayList filter(String title) {

        ArrayList<Comment> queryset = new ArrayList<Comment>();

        // If blank string, filter for all
        if (title.equals("")) {
            queryset = commentList;
        } else {

            // Get any comment items from commentList that are a part of discussion_title
            // and add them to the queryset
            for(int i = 0; i < commentList.size(); i++){
                comment c = commentList.get(i);
                Discussion c_discussion = c.getDiscussion();

                Log.c("COMM_MANAGER", "discussion_comment: " + c_discussion.getTitle());

                if(c_discussion != null && c_discussion.getTitle().equals(title) && !queryset.contains(c)){
                    queryset.add(c);
                    Log.c("COMM_MANAGER", "Comment added to queryset: " + c_discussion.getTitle());
                }
            }
        }

        return queryset;
    }
    @Override
    public void add(Object item) {
        commentList.add((Comment) item);
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

}
