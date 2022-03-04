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


public class DiscussionsManager implements BaseManager{

    private static ArrayList<Discussion> discussionList;

    public DiscussionsManager(){

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


    public void addDiscussion(Discussion d){
        discussionList.add(d);
    }


    //returns the number of discussions
    public int size(){
        return discussionList.size();
    }

    public ArrayList<Discussion> getList(){
        return discussionList;
    }




}
