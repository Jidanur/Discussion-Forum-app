package com.example.simple_forum.controller.managers;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.controller.validator.Topic_validate;
import com.example.simple_forum.controller.validator.Validation;
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

    private static ArrayList<Topic> topic_list = new ArrayList<Topic>();

    public TopicManager(){}

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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_str(String data){
        JSONArray topics = JSONParser.get_json(data);

        // Iterate through serialized objects and create topic models
        for(int i = 0; i < topics.length(); i++) {
            try {

                // Get json object
                JSONObject topic = topics.getJSONObject(i);

                // TODO
                // Query for user model to create a new entry

                // Create topic model
                Topic t = new Topic(topic.get("title").toString(), new User(), topic.get("date_created").toString());

                // Add to the list
                add(t);

            } catch (JSONException e) {
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }
    }

    // Add a new topic to the list
    public void add(Object item){

        // Cast item
        Topic t = (Topic) item;

        // TODO
        // Serialize item and add to json file "topics.json"
        // Write a method in the json parser to do this

        // TODO
        // add(t) should return true or false if it was added via api successfully

        // Make sure title does not exist already
        if( !exists(t.getTitle()) ){
            //validation
            Validation topic_val = new Topic_validate(t);
            if(topic_val.validate()) {
                // Add the topic object to the list
                topic_list.add(t);
            }
        }

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

    // Get by object
    @Override
    public Object get(Object item) {
        int index  = topic_list.indexOf((Topic) item);
        Topic t = null;

        if (index != -1){
            t = topic_list.get(index);
        }
        return t;
    }

    // Get size
    public int size(){
        return topic_list.size();
    }

    // Return the array list
    public ArrayList get_list(){
        return topic_list;
    }

    @Override
    public Boolean exists(String text) {
        // Iterate through array list
        for(int i = 0; i < topic_list.size(); i++){
            if(topic_list.get(i).getTitle().equals(text)){
                return true;
            }
        }
        return false;
    }

    // Clears array list
    @Override
    public void clear() {
        topic_list.clear();
    }
}
