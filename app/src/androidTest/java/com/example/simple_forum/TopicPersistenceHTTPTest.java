package com.example.simple_forum;


import static org.junit.Assert.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.HTTP.TopicPersistenceHTTP;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class TopicPersistenceHTTPTest {

    TopicPersistenceHTTP tp;
    HttpUtils http;
    User stub_user;

    @Before
    public void init(){
        // Init http based topic persistence
        tp = new TopicPersistenceHTTP();
        http = new HttpUtils();

        // Stub values
        stub_user = new User(1, "stub_name", "stub_pass", "stub@mail.com", "stub bio");
    }

    @Test
    public void test_get_all(){

        // Get the JSON objects from the API
        JSONArray topics_json = http.get(SF_API.TOPICS);
        int tj_size = topics_json.length();

        // Topics retrieved from the JSON object
        ArrayList<Topic> topic_list = tp.get_all();
        int tl_size = topic_list.size();

        // Size of both should be the same
        assertTrue("JSON SIZE: " + tj_size + " | ARRAY LIST SIZE: " + tl_size,
                tl_size == tj_size );
    }

    @Test
    public void test_get_single_id() throws JSONException {

        // Get an entry from the API manually
        JSONArray topics = http.get(SF_API.TOPICS);
        JSONObject topic = topics.getJSONObject(0);

        // Build topic obj
        // Set users as the controlled stub user instead of from the
        // User manager
        int id = topic.getInt("id");
        String title = topic.getString("title");
        String date_created = topic.getString("date_created");
        stub_user.setId(topic.getInt("user"));
        Topic t1 = new Topic(id, title, stub_user, date_created);

        // Get the same entry from the topic persistence using id
        Topic t2 = tp.get(id);
        t2.setUser(stub_user);

        // Compare serialized data
        // Both should be the same
        String t1_ser = t1.serialize().toString();
        String t2_ser = t2.serialize().toString();
        assertTrue("T1: " + t1_ser + " | T2: " + t2_ser, t1_ser.equals(t2_ser));
    }

    @Test
    public void test_get_single() throws JSONException {

        // Get an entry from the API manually
        JSONArray topics = http.get(SF_API.TOPICS);
        JSONObject topic = topics.getJSONObject(0);

        // Build topic obj
        // Set users as the controlled stub user instead of from the
        // User manager
        int id = topic.getInt("id");
        String title = topic.getString("title");
        String date_created = topic.getString("date_created");
        stub_user.setId(topic.getInt("user"));
        Topic t1 = new Topic(id, title, stub_user, date_created);

        // Get an entry from the topic persistence
        Topic t2 = tp.get(title);
        t2.setUser(stub_user);

        // Compare serialized data
        // Both should be the same
        String t1_ser = t1.serialize().toString();
        String t2_ser = t2.serialize().toString();
        assertTrue("T1: " + t1_ser + " | T2: " + t2_ser, t1_ser.equals(t2_ser));
    }

    @Test
    public void test_get_count(){

        // Get all topic objects from api
        JSONArray topics_j = http.get(SF_API.TOPICS);
        int t1 = topics_j.length();

        // Get all topics loaded into array lists
        int t2 = tp.get_all().size();

        // Compare
        // Both should be the same
        assertTrue("API TOPICS SIZE: " + t1 + " | LOADED SIZE: " + t2,t1 == t2);
    }
}
