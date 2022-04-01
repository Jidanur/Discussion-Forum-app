package com.example.simple_forum.controller.managers;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.controller.application.Services;
import com.example.simple_forum.controller.persistence.TopicPersistenceHSQLDB;
import com.example.simple_forum.controller.validator.Topic_validate;
import com.example.simple_forum.controller.validator.Validation;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// Topic manager class for CRUD operations
// implements interface BaseManager
public class TopicManager implements BaseManager {

    private static ArrayList<Topic> topic_list = new ArrayList<Topic>();
    private boolean use_persistence;
    private static TopicPersistenceHSQLDB tp;

    public TopicManager() {

        this.tp = null;
        use_persistence = false;
    }

    public TopicManager(boolean use_persistence){
        this.use_persistence = use_persistence;

        if(use_persistence){
            tp = (TopicPersistenceHSQLDB) Services.getTopicPersistence();
            topic_list = tp.get_all();
        }
    }


    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String filename, Context context) {

        // Init JSON parser
        JSONArray topics = JSONParser.get_json(context, filename);

        // Clear manager first
        TopicManager t_manager = new TopicManager();
        t_manager.clear();

        // Iterate through serialized objects and create topic models
        for (int i = 0; i < topics.length(); i++) {
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

    // Add a collection of json entries from a string
    // Ignore for now until our API is available for use
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_str(String data) {
        JSONArray topics = JSONParser.get_json(data);

        // Iterate through serialized objects and create topic models
        for (int i = 0; i < topics.length(); i++) {
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
    public void add(Object item) {

        // Cast item
        Topic t = (Topic) item;
        System.out.println(t);

        // TODO
        // add(t) should return true or false if it was added via api successfully

        // Make sure title does not exist already
        Validation topic_val = new Topic_validate(t);

        if (topic_val.validate() && !exists(t.getTitle())) {

            // Add the topic object to the list
            if (use_persistence) {
                tp.insert_topic(t);
                topic_list = tp.get_all();
            } else {
                topic_list.add(t);
            }
        }
    }

    // Get a topic by title
    public Topic get(String title) {

        for (Topic t : topic_list) {
            if (t.getTitle().equals(title)) {
                return t;
            }
        }

        return null;
    }

    // Get a topic by position
    public Topic get(int pos) {
        return topic_list.get(pos);
    }

    // Get by object
    @Override
    public Object get(Object item) {
        int index = topic_list.indexOf((Topic) item);
        Topic t = null;

        if (index != -1) {
            t = topic_list.get(index);
        }
        return t;
    }

    // Get size
    public int size() {
        return topic_list.size();
    }

    // Return the array list
    public ArrayList get_list() {
        return topic_list;
    }

    @Override
    public Boolean exists(String text) {
        // Iterate through array list
        for (int i = 0; i < topic_list.size(); i++) {
            Topic item = topic_list.get(i);
            if (item != null && item.getTitle().equals(text)) {
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

    public void add_json_file(String filepath) {
    }
}
