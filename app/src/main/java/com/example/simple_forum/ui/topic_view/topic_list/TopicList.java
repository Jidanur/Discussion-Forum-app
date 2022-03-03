package com.example.simple_forum.ui.topic_view.topic_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.adapters.TopicRecyclerAdapter;
import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class TopicList extends AppCompatActivity {

    String[] test_data = {"DATA1", "DATA2", "DATA3", "DATA4"};
    ArrayList<Topic> topic_list;
    private RecyclerView topic_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);

        // TODO
        // Populate topic list via json api call

        // Create Topic objects from parsed json file
        JSONArray topics = JSONParser.get_json(getApplicationContext(), "topics.json");
        topic_list = new ArrayList<Topic>();
        for(int i = 0; i < topics.length(); i++){
            try {
                JSONObject obj = topics.getJSONObject(i);

                // TODO
                // Find user and match the obj argument
                Topic t = new Topic( obj.get("title").toString(), new User(), obj.get("date_created").toString() );

                // Add item to the list
                topic_list.add(t);
            } catch(JSONException e) {
                Log.i("TOPIC_LIST", e.getMessage());
            }
        }

        // Set the recycler
        topic_recycler = findViewById(R.id.topic_list);

        // Set the adapter for the recycler
        set_adapter();

    }

    private void set_adapter() {

        // TODO
        // Parse JSON file and pass in parsed Topic Objects as an array list
        // Create recycler instance
        TopicRecyclerAdapter topic_adapter = new TopicRecyclerAdapter(topic_list);

        // Create linear layout manager
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        topic_recycler.setLayoutManager(layout_manager);
        topic_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        topic_recycler.setAdapter(topic_adapter);
    }
}