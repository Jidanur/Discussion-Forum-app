package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.interfaces.IUserPersistence;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserPersistenceHTTP implements IUserPersistence {

    private static HttpUtils http;
    private static SF_API endpoint = SF_API.USERS;

    public UserPersistenceHTTP(){ http = new HttpUtils(); }

    @Override
    public void insert_user(User u) {

        // Serialize data before sending
        http.post(endpoint, u.serialize());
    }

    @Override
    public void delete_user(User u) {
        // nothing happens here
    }

    @Override
    public User get(String username) {

        // Find user
        for(User u : get_all()){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    @Override
    public User get(int id) {

        // Find user
        for(User u : get_all()){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }


    @Override
    public ArrayList<User> get_all() {

        // Result list
        ArrayList<User> user_list = new ArrayList<>();

        // Get all items from the endpoint as JSON
        JSONArray users = http.get(endpoint);
        JSONArray user_profiles = http.get(SF_API.USER_PROFILES);

        // Convert from json to user objects
        for(int i = 0; i < users.length(); i ++){

            try {
                // Extract key value pairs from users
                JSONObject obj = users.getJSONObject(i);
                int id = obj.getInt("id");
                String username = obj.getString("username");
                String password = obj.getString("password");
                String email = obj.getString("email");
                String bio = "";

                // Build the user object
                User u = new User(id, username, password, email, bio);

                // Get the user profile if possible
                JSONObject u_profile = user_profiles.getJSONObject(i);
                if(id == u_profile.getInt("user")){
                    u.setBio( u_profile.getString("bio") );
                } else {
                    System.out.println("ID mismatch in user profile sync");
                }

                // Add the user to the list
                user_list.add(u);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return user_list;
    }

    @Override
    public int get_count() {
        return http.get(endpoint).length();
    }

    @Override
    public boolean auth_user(User u) {
        return false;
    }
}
