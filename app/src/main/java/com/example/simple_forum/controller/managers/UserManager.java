package com.example.simple_forum.controller.managers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserManager implements BaseManager{

    private static ArrayList<User> userList= new ArrayList<User>();

    public UserManager(){}

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String fileName, Context context){

        JSONArray users = JSONParser.get_json(context,fileName);

        for(int i = 0; i < users.length(); i++){
            try{

                // Get json object
                JSONObject curr_user = users.getJSONObject(i);

                // Create user model
                User newUser = new User(curr_user.get("username").toString(), curr_user.get("password").toString(), curr_user.get("email").toString(),"");

                add(newUser);


            } catch (JSONException e){
                Log.i("User_list_error", e.getMessage());
            }
        }
    }


    // Add a collection of json entries from a string
    // Ignore for now until our API is available for use
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_str(String data){
        JSONArray users = JSONParser.get_json(data);

        // Iterate through serialized objects and create user models
        for(int i = 0; i < users.length(); i++) {
            try {

                // Get json object
                JSONObject user = users.getJSONObject(i);


                // Create user model
                User u = new User(user.get("username").toString(), user.get("password").toString(), user.get("email").toString(),"");

                // Add to the list
                add(u);

            } catch (JSONException e) {
                Log.i("USER_LIST", e.getMessage());
            }
        }
    }

    // Add a new user to the list
    public void add(Object item){

        // Cast item
        User u = (User) item;

        // TODO
        // Validate

        // TODO
        // add(u) should return true or false if it was added via api successfully

        // Make sure user does not exist already
        if( !exists(u.getUsername()) ){
            // Add the topic object to the list
            userList.add(u);
        }

    }

    // Get a user by position
    public Object get(int pos){
        return userList.get(pos);
    }

    // Get by object
    @Override
    public Object get(Object item) {
        int index  = userList.indexOf((User) item);
        User u = null;

        if (index != -1){
            u = userList.get(index);
        }
        return u;
    }

    // Get size
    public int size(){
        return userList.size();
    }

    // Return the array list
    public ArrayList get_list(){
        return userList;
    }

    @Override
    public Boolean exists(String text) {
        // Iterate through array list
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUsername().equals(text)){
                return true;
            }
        }
        return false;
    }

    // Clears array list
    @Override
    public void clear() {
        userList.clear();
    }


}
