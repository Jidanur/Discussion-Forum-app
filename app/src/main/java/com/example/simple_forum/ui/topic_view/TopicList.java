package com.example.simple_forum.ui.topic_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.adapters.TopicRecyclerAdapter;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;
import com.example.simple_forum.ui.discussion_view.DiscussionList;

public class TopicList extends AppCompatActivity implements TopicRecyclerAdapter.OnTopicListener {

    private TopicManager t_manager;
    private RecyclerView topic_recycler;
    private TopicRecyclerAdapter topic_adapter;

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

        // Create recycler instance
        topic_adapter = new TopicRecyclerAdapter(t_manager, this);

        // Create linear layout manager
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        topic_recycler.setLayoutManager(layout_manager);
        topic_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        topic_recycler.setAdapter(topic_adapter);
    }

    // Create topic on button click
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void create_topic(View view){

        // Get the text from the input field
        EditText text_input = (EditText) findViewById(R.id.topic_create_input);
        String new_topic = text_input.getText().toString();

        // TODO
        // Validate input

        // Create a new topic object
        Topic t = new Topic(new_topic, new User(), "2022-02-28T00:22:58.538787Z");

        // Add the topic to the topic manager
        t_manager.add(t);

        // Notify the adapter of the change
        topic_adapter.notifyDataSetChanged();

    }

    @Override
    public void onTopicClick(int position) {

        // Start a new intent and pass in the Topic object
        Intent discussion_list = new Intent(this, DiscussionList.class);

        // Pass the topic title as an extra arg to the activity
        String t = t_manager.get(position).getTitle();
        Log.d("TOPIC_LIST", "onTopicClick: " + t);
        discussion_list.putExtra("TOPIC_TITLE", t);
        startActivity(discussion_list);
    }
}
