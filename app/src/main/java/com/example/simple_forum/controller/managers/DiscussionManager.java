package com.example.simple_forum.controller.managers;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DiscussionManager implements BaseManager, FilterManager {

    private static ArrayList<Discussion> discussionList = new ArrayList<Discussion>();

    public DiscussionManager() {}

    // Add a collection of json entries from a file
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add_json_file(String fileName, Context context) {

        JSONArray discussions = JSONParser.get_json(context, fileName);

        for (int i = 0; i < discussions.length(); i++) {
            try {

                // Get json object
                JSONObject curr_discussion = discussions.getJSONObject(i);

                // Get the topic object
                TopicManager t_manager = new TopicManager();
                Topic t = t_manager.get(curr_discussion.get("topic").toString());

                // TODO
                // Get the user

                Discussion newDiscussion = new Discussion(t, curr_discussion.get("title").toString(), curr_discussion.get("content").toString(), new User(), curr_discussion.get("date_created").toString());

                add((Object) newDiscussion);

            } catch (JSONException e) {
                Log.i("Discussion_list_error", e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void add_json_str(String data) {
        JSONArray discussions = JSONParser.get_json(data);

        // Iterate through serialized objects and create disc models
        for (int i = 0; i < discussions.length(); i++) {
            try {

                // Get json object
                JSONObject disc = discussions.getJSONObject(i);


                // TODO
                // Query for user model to create a new entry

                // Create disc model
                Discussion t = new Discussion(null, disc.get("title").toString(), disc.get("content").toString(), new User(), disc.get("date_created").toString());

                // Add to the list
                add(t);

            } catch (JSONException e) {
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }
    }

    // get discussionList of a specific user
    public ArrayList<Discussion> get(User user) {

        ArrayList<Discussion> userDiscussionList = new ArrayList<Discussion>();

        for (int i = 0; i < discussionList.size(); i++) {

            Discussion curr = discussionList.get(i);
            if (curr.getUser() == user) {
                userDiscussionList.add(curr);
            }
        }

        return userDiscussionList;

    }

    // Filter discussion queryset by topic title
    // Blank string implies all
    public ArrayList filter(String title) {

        ArrayList<Discussion> queryset = new ArrayList<Discussion>();

        // If blank string, filter for all
        if (title.equals("")) {
            queryset = discussionList;
        } else {

            // Get any discussion items from discussionList that are a part of topic_title
            // and add them to the queryset
            for(int i = 0; i < discussionList.size(); i++){
                Discussion d = discussionList.get(i);
                Topic d_topic = d.getTopic();

                Log.d("DISC_MANGER", "discussion: " + d.getTitle());

                if(d_topic != null && d_topic.getTitle().equals(title) && !queryset.contains(d)){
                    queryset.add(d);
                    Log.d("DISC_MANGER", "discussion added to queryset: " + d.getTitle());
                }
            }
        }

        return queryset;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void new_discussion(String topic, String title, String content){

        // TODO
        // Get the current logged in user via user manager
        User user = new User();

        // Get topic via topic manager
        TopicManager t_manager = new TopicManager();
        Topic t = t_manager.get(topic);

        // Set the time created
        Date date_time_now = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date_time = format.toString();

        // TODO
        // Make sure topic and user is not null
        if(topic != null){
            // Create Discussion object
            Discussion d = new Discussion(t, title, content, user, date_time);

            // Add it to the discussion list via the built in add() function through discussion manager
            add(d);
        }
    }

    @Override
    public void add(Object item) {

        // Cast item
        Discussion d = (Discussion) item;

        // Make sure item does not exist yet
        if ( !exists(d.getTitle()) ) {

            // TODO
            // Validate

            // TODO
            // Serialize item and add to json file "discussions.json"
            // Write a method in the json parser to do this

            // TODO
            // add(t) should return true or false if it was added via api successfully
            Log.d("DISC_MANGER", "discussion added: " + d.getTitle());

            discussionList.add(d);
        }
    }

    @Override
    public Object get(int pos) {
        return discussionList.get(pos);
    }

    @Override
    // Get by object
    public Object get(Object item) {

        int index = discussionList.indexOf((Discussion) item);
        Discussion d = null;

        if (index != -1) {
            d = discussionList.get(index);
        }
        return d;
    }

    //returns the number of discussions
    public int size() {
        return discussionList.size();
    }

    @Override
    public ArrayList get_list() {
        return discussionList;
    }

    @Override
    public Boolean exists(String text) {

        // Iterate through array list
        for (int i = 0; i < discussionList.size(); i++) {
            if (discussionList.get(i).getTitle().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        discussionList.clear();
    }
}
