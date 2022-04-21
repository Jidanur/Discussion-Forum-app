package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.HttpUtilsAsync;
import com.example.simple_forum.controller.http_connector.IHTTPUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class TopicPersistenceHTTP implements ITopicPersistence {

    private static IHTTPUtils http;
    private static SF_API endpoint = SF_API.TOPICS;

    private static UserPersistenceHTTP up;

    public TopicPersistenceHTTP(){
        http = new HttpUtils();
        up = new UserPersistenceHTTP();
    }

    // Insert topic via API to server
    @Override
    public void insert_topic(Topic t) {

        // Serialize data as the body content
        // before sending off to the endpoint
        http.post(endpoint, t.serialize());
    }

    @Override
    public void delete_topic(Topic t) {
        // Nothing happens here
    }

    @Override
    public Topic get(String title) {

        // Iterate and find
        for(Topic t : get_all()){
            if(t.getTitle().equals(title)){
                return t;
            }
        }
        return null;
    }

    @Override
    public Topic get(int id) {
        // Iterate and find
        for(Topic t : get_all()){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Topic> get_all() {

        // Result list
        ArrayList<Topic> topic_list = new ArrayList<>();

        // Get all items as json from the endpoint
        JSONArray topics = http.get(endpoint);

        // Convert from json objects to topic objects
        for(int i = 0; i < topics.length(); i++){
            try {

                // Extract key value pairs
                JSONObject obj = topics.getJSONObject(i);
                int id = obj.getInt("id");
                String title = obj.getString("title");
                String date_created = obj.getString("date_created");

                // Build and add new topic
                // We can skip user here as it is not needed
                Topic t = new Topic(id, title, date_created);
                topic_list.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return topic_list;
    }

    @Override
    public int get_count() {
        return http.get(endpoint).length();
    }
}
