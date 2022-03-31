package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.ui.adapters.DiscussionRecyclerAdapter;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class DiscussionListActivity extends AppCompatActivity {

    private DiscussionManager disc_manager;
    private String topic;
    private RecyclerView disc_recycler;
    private DiscussionRecyclerAdapter disc_adapter;
    private Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_list);

        // TODO
        // Populate discussion list via json api call

        // Get intent
        intent = getIntent();

        // Set topic
        topic = intent.getStringExtra("TOPIC_TITLE");

        // Create a discussion manager and parse json file for now
        disc_manager = new DiscussionManager();
        disc_manager.add_json_file("discussions.json", getApplicationContext());

        // Set the recycler view
        disc_recycler = findViewById(R.id.discussion_list);

        // Set adapter
        set_adapter();

        // Set the topic title text of the view
        TextView topic_title = (TextView) findViewById(R.id.discussion_topic_title);
        topic_title.setText(topic);
    }

    private void set_adapter() {

        // Create recycler instance
        disc_adapter = new DiscussionRecyclerAdapter(disc_manager, topic);

        // Create linear layout manger
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        disc_recycler.setLayoutManager(layout_manager);
        disc_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        disc_recycler.setAdapter(disc_adapter);
    }

    // Clicked new discussion button
    public void new_discussion(View view){

        // Open new discussion form
        Intent intent = new Intent(this, NewDiscussionFormActivity.class);

        // Pass extra data
        intent.putExtra("TOPIC_TITLE", topic);

        // Start activity
        startActivity(intent);
    }

    // Clicked back to topics
    public void back_to_topics(View view){

        // Start intent
        Intent topics_list = new Intent(this, TopicListActivity.class);
        startActivity(topics_list);
    }
}