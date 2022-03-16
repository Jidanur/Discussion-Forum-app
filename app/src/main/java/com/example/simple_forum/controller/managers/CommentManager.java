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


public class CommentManager implements BaseManager{

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


                Comment newComment = new Comment(curr_comment.get("content").toString(),new User(),curr_comment.get("date_created").toString());

                commentList.add(newComment);


            } catch (JSONException e){
                Log.i("Discussion_list_error", e.getMessage());
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
                Comment c = new Comment(comm.get("content").toString(), new User(), comm.get("date_created").toString());

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

