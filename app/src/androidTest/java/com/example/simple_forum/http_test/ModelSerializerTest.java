package com.example.simple_forum.http_test;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ModelSerializerTest {

    User stub_user;
    Topic stub_topic;
    Discussion stub_disc;
    String stub_date;

    @Before
    public void init(){
        stub_date = "2022-04-12 00:00:00.000000";
        stub_user = new User(1, "stub1", "stub_password123", "stub@1.com", "stub bio");
        stub_topic = new Topic(1, "stub topic", stub_user, stub_date);
        stub_disc = new Discussion(stub_topic, "stub title", "stub content", stub_user, stub_date);
    }

    @Test
    public void test_user_serializer() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("username","test_user");
        obj.put("password","test_pass123");
        obj.put("email","test@test.com");
        obj.put("bio","test bio");

        // Create user with the same values
        User u = new User(obj.get("username").toString(), obj.get("password").toString(),
                obj.get("email").toString(), obj.get("bio").toString());

        JSONObject serial = u.serialize();
        assertTrue("BASE: " + obj.toString() + " | SERIALIZED: " + serial.toString(),
                obj.toString().equals(serial.toString()));
    }

    @Test
    public void test_topic_serializer() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("title","test title");
        obj.put("user",stub_user.getId());

        // Create a topic with the same values
        Topic t = new Topic(obj.get("title").toString(), stub_user, stub_date);

        JSONObject serial = t.serialize();
        assertTrue("BASE: " + obj.toString() + " | SERIALIZED: " + serial.toString(),
                obj.toString().equals(serial.toString()));
    }

    @Test
    public void test_discussion_serializer() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("topic", stub_topic.getId());
        obj.put("title", "test discussion");
        obj.put("content", "test content");
        obj.put("user", stub_user.getId());

        // Create a discussion with the same values
        Discussion d = new Discussion(stub_topic, obj.get("title").toString(), obj.get("content").toString(),
                stub_user, stub_date);

        JSONObject serial = d.serialize();
        assertTrue("BASE: " + obj.toString() + " | SERIALIZED: " + serial.toString(),
                obj.toString().equals(serial.toString()));
    }

    @Test
    public void test_comment_serializer() throws JSONException {

        JSONObject obj = new JSONObject();
        obj.put("discussion", stub_disc.getId());
        obj.put("content", "test content");
        obj.put("user", stub_user.getId());

        // Create a comment with the same values
        Comment c = new Comment(stub_disc, obj.get("content").toString(), stub_user, stub_date);

        JSONObject serial = c.serialize();
        assertTrue("BASE: " + obj.toString() + " | SERIALIZED: " + serial.toString(),
                obj.toString().equals(serial.toString()));
    }

}

