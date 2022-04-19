package com.example.simple_forum;


import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;

import org.json.JSONArray;
import static org.junit.Assert.*;
import android.util.Log;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HTTPUtilsTest {

    HttpUtils http;
    String TAG = "HTTP_UTILS TEST";

    @Before
    public void init(){
        http = new HttpUtils();
    }

    @Test
    public void test_connect() {

        // Try to get get a response from server
        boolean status = http.get_server_status();
        assertTrue("Server online?: " + status, status);
    }

    @Test
    public void test_get_discussions() throws JSONException {

        // Try to get discussions
        JSONArray content = http.get(SF_API.DISCUSSIONS);
        int size = content.length();
        for(int i = 0; i < size; i++){
            Log.i(TAG, "test_get: Discussion: " + content.getJSONObject(i).toString());
        }
        assertTrue("Content size: " + size, size != 0);
    }

    @Test
    public void test_get_topics() throws JSONException {

        // Try to get discussions
        JSONArray content = http.get(SF_API.TOPICS);
        int size = content.length();
        for(int i = 0; i < size; i++){
            Log.i(TAG, "test_get: Topic: " + content.getJSONObject(i).toString());
        }
        assertTrue("Content size: " + size, size != 0);
    }

    @Test
    public void test_get_comments() throws JSONException {

        // Try to get discussions
        JSONArray content = http.get(SF_API.COMMENTS);
        int size = content.length();
        for(int i = 0; i < size; i++){
            Log.i(TAG, "test_get: Comments: " + content.getJSONObject(i).toString());
        }
        assertTrue("Content size: " + size, size != 0);
    }

    @Test
    public void test_get_users() throws JSONException {

        // Try to get discussions
        JSONArray content = http.get(SF_API.USERS);
        int size = content.length();
        for(int i = 0; i < size; i++){
            Log.i(TAG, "test_get: Users: " + content.getJSONObject(i).toString());
        }
        assertTrue("Content size: " + size, size != 0);
    }

    @Test
    public void test_get_user_profiles() throws JSONException {

        // Try to get discussions
        JSONArray content = http.get(SF_API.USER_PROFILES);
        int size = content.length();
        for(int i = 0; i < size; i++){
            Log.i(TAG, "test_get: USER PROFILES: " + content.getJSONObject(i).toString());
        }
        assertTrue("Content size: " + size, size != 0);
    }
}
