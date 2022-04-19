package com.example.simple_forum;

import static org.junit.Assert.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.HTTP.UserPersistenceHTTP;
import com.example.simple_forum.models.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserPersistenceHTTPTest {

    UserPersistenceHTTP up;
    HttpUtils http;

    @Before
    public void init(){

        // Init http user persistence
        up = new UserPersistenceHTTP();
        http = new HttpUtils();
    }

    @Test
    public void test_get_all(){

        // Get item count from api
        int uj_size = http.get(SF_API.USERS).length();

        // Get item count from build array list
        int ua_size = up.get_all().size();

        // Both should be the same
        assertTrue("API SIZE: " + uj_size + " | LOADED SIZE: " + ua_size, ua_size == uj_size);
    }

    @Test
    public void test_user_profile_sync(){
        // Get counts
        int users_count = http.get(SF_API.USERS).length();
        int user_profiles_count = http.get(SF_API.USER_PROFILES).length();

        // Are the user lists in sync with the user profile list?
        assertTrue("USERS: " + users_count + " | USER PROFILES: " + user_profiles_count,
                user_profiles_count == users_count);
    }

    @Test
    public void test_get_single() throws JSONException {

        // Get an entry from api manually, both users and user profiles
        JSONObject user = http.get(SF_API.USERS).getJSONObject(0);
        JSONObject user_profile = http.get(SF_API.USER_PROFILES).getJSONObject(0);

        // Build a user model
        int id = user.getInt("id");
        String username = user.getString("username");
        String password = user.getString("password");
        String email = user.getString("email");
        String bio = user_profile.getString("bio");

        // Build the user object
        User u = new User(id, username, password, email, bio);

        // Get a user from the build array list
        User u2 = up.get(username);

        // Compare serialized data
        String serial_1 = u.serialize().toString();
        String serial_2 = u2.serialize().toString();

        assertTrue("U1: " + serial_1 + " | U2: " + serial_2, serial_1.equals(serial_2));
    }

    @Test
    public void test_get_count(){
        // Get count from the api
        int u1 = http.get(SF_API.USERS).length();

        // Get count from the built list
        int u2 = up.get_all().size();

        // Compare
        assertTrue("API TOPICS SIZE: " + u1 + " | LOADED SIZE: " + u2,u1 == u2);
    }
}
