package com.example.simple_forum.controller.managers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import com.example.simple_forum.controller.JSONParser;
import  com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DiscussionManager implements BaseManager{

    private static ArrayList<Discussion> discussionList;

    public DiscussionManager(){

        discussionList = new ArrayList<Discussion>();
    }

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String fileName, Context context){

        JSONArray discussions = JSONParser.get_json(context,fileName);

        for(int i = 0; i < discussions.length(); i++){
            try{

                // Get json object
                JSONObject curr_discussion = discussions.getJSONObject(i);


                Discussion newDiscussion = new Discussion(curr_discussion.get("title").toString(),curr_discussion.get("content").toString(),new User(),curr_discussion.get("date_created").toString(),null);

                discussionList.add(newDiscussion);


            } catch (JSONException e){
                Log.i("Discussion_list_error", e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void add_json_str(String data) {
        JSONArray discussions = JSONParser.get_json(data);

        // Iterate through serialized objects and create disc models
        for(int i = 0; i < discussions.length(); i++) {
            try {

                // Get json object
                JSONObject disc = discussions.getJSONObject(i);

                // TODO
                // Query for user model to create a new entry

                // Create disc model
                Discussion t = new Discussion(disc.get("title").toString(), disc.get("content").toString(), new User(), disc.get("date_created").toString(), null);

                // Add to the list
                add(t);

            } catch (JSONException e) {
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }
    }

    // get discussionList of a specific user
    public ArrayList<Discussion> get(User user){

        ArrayList<Discussion> userDiscussionList = new ArrayList<Discussion>();

        for(int i =0;i<discussionList.size();i++){

            Discussion curr = discussionList.get(i);
            if(curr.getUser() == user){
                userDiscussionList.add(curr);
            }
        }

        return userDiscussionList;

    }


    @Override
    public void add(Object item) {
        discussionList.add((Discussion) item);
    }

    @Override
    public Object get(int pos) {
        return discussionList.get(pos);
    }

    @Override
    // Get by object
    public Object get(Object item) {

        int index = discussionList.indexOf((Discussion) item);
        Discussion d = null;

        if(index != -1){
            d = discussionList.get(index);
        }
        return d;
    }

    //returns the number of discussions
    public int size(){
        return discussionList.size();
    }

    @Override
    public ArrayList get_list() {
        return discussionList;
    }
}
