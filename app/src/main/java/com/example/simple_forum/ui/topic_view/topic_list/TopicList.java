package com.example.simple_forum.ui.topic_view.topic_list;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.adapters.TopicRecyclerAdapter;
import com.example.simple_forum.controller.JSONParser;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TopicList extends AppCompatActivity {

    TopicManager t_manager;
    private RecyclerView topic_recycler;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);

        // TODO
        // Populate topic list via json api call

        // Create Topic manager and parse json test file
        t_manager = new TopicManager();
        t_manager.add_json_file("topics.json", getApplicationContext());

        // Set the recycler
        topic_recycler = findViewById(R.id.topic_list);

        // Set the adapter for the recycler
        set_adapter();
    }

    private void set_adapter() {

        // TODO
        // Parse JSON file and pass in parsed Topic Objects as an array list
        // Create recycler instance
        TopicRecyclerAdapter topic_adapter = new TopicRecyclerAdapter(t_manager);

        // Create linear layout manager
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        topic_recycler.setLayoutManager(layout_manager);
        topic_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        topic_recycler.setAdapter(topic_adapter);
    }
}
