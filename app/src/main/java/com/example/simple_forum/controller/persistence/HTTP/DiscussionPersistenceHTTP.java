package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.HttpUtilsAsync;
import com.example.simple_forum.controller.http_connector.IHTTPUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DiscussionPersistenceHTTP implements IDiscussionPersistence {

    private static IHTTPUtils http;
    private static SF_API endpoint = SF_API.DISCUSSIONS;

    private static UserPersistenceHTTP up;
    private static TopicPersistenceHTTP tp;

    public DiscussionPersistenceHTTP(){
        http = new HttpUtils();;
        up = new UserPersistenceHTTP();
        tp = new TopicPersistenceHTTP();
    }

    @Override
    public void insert_disc(Discussion d) {
        // Serialize data before sending
        http.post(endpoint, d.serialize());
    }

    @Override
    public void delete_disc(Discussion t) {
        // Nothing happens here
    }

    @Override
    public Discussion get(String title) {

        // Find the discussion
        for(Discussion d : get_all()){
            if(title.equals(d.getTitle())){
                return d;
            }
        }
        return null;
    }

    @Override
    public Discussion get(int id) {

        // Find the discussion
        for(Discussion d : get_all()){
            if(id == d.getId()){
                return d;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Discussion> get_all() {

        // Result list
        ArrayList<Discussion> disc_list = new ArrayList<>();

        // HTTP managers --set false for local to use HTTP
        UserManager um = new UserManager(false);

        // Get all the items from the endpoint as json objects
        JSONArray discs = http.get(endpoint);

        // Convert from JSON to discussion models
        for(int i = 0; i < discs.length(); i++){

            try {
                // Extract key value pair from discussions
                JSONObject obj = discs.getJSONObject(i);
                int id = obj.getInt("id");
                String title = obj.getString("title");
                String content = obj.getString("content");
                String date_created = obj.getString("date_created");
                User u = up.get(obj.getInt("user"));
                Topic t = tp.get(obj.getInt("topic"));

                // Add the new discussion
                Discussion d = new Discussion(id, t, title, content, u, date_created);
                disc_list.add(d);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return disc_list;
    }

    @Override
    public int get_count() {
        return http.get(endpoint).length();
    }
}
