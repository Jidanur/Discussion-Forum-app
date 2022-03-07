package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.adapters.DiscussionRecyclerAdapter;
import com.example.simple_forum.controller.managers.DiscussionManager;

public class DiscussionList extends AppCompatActivity {

    private DiscussionManager disc_manager;
    private RecyclerView disc_recycler;
    private DiscussionRecyclerAdapter disc_adapter;
    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_list);

        // TODO
        // Populate discussion list via json api call

        // Set intent
        intent = getIntent();

        // Create a discussion manager and parse json file for now
        disc_manager = new DiscussionManager();
        disc_manager.add_json_file("discussions.json", getApplicationContext());

        // Set the recycler view
        disc_recycler = findViewById(R.id.discussion_list);

        // Set adapter
        set_adapter();

        // Set the topic title text of the view
        TextView topic_title = (TextView) findViewById(R.id.discussion_topic_title);
        topic_title.setText(intent.getStringExtra("TOPIC_TITLE"));
    }

    private void set_adapter() {

        // Create recycler instance
        disc_adapter = new DiscussionRecyclerAdapter(disc_manager);

        // Create linear layout manger
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        disc_recycler.setLayoutManager(layout_manager);
        disc_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        disc_recycler.setAdapter(disc_adapter);
    }
}