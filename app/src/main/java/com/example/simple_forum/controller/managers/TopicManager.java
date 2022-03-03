package com.example.simple_forum.controller.managers;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.util.ArrayList;

// Topic manager class for CRUD operations
// implements interface BaseManager
public class TopicManager implements BaseManager{

    private static ArrayList<Topic> topic_list;

    public TopicManager(){

        // Init list
        topic_list = new ArrayList<Topic>();
    }

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String filename, Context context){

        // Init JSON parser
        JSONArray topics = JSONParser.get_json(context, filename);

        // Iterate through serialized objects and create topic models
        for(int i = 0; i < topics.length(); i++){
            try{

                // Get json object
                JSONObject topic = topics.getJSONObject(i);

                // TODO
                // Query for user model to create a new entry

                // Create topic model
                Topic t = new Topic(topic.get("title").toString(), new User(), topic.get("date_created").toString());

                // Add to the list
                add(t);

            } catch (JSONException e){
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }
    }

    // Add a collection of json entries from a string
    // Ignore for now until our API is available for use
    public void add_json_str(String line){

    }

    // Add a new topic to the list
    public void add(Topic t){
        topic_list.add(t);
    }

    // Get a topic by title
    public Topic get(String title){

        for(Topic t : topic_list){
            if(t.getTitle().equals(title)){
                return t;
            }
        }

        return null;
    }

    // Get a topic by position
    public Topic get(int pos){
        return topic_list.get(pos);
    }

    // Get size
    public int size(){
        return topic_list.size();
    }

    // Return the array list
    public ArrayList get_list(){
        return topic_list;
    }

}
