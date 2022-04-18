package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

public class NewDiscussionFormActivity extends AppCompatActivity {

    private String topic;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_discussion_form);

        // Get intent
        intent = getIntent();

        // Set topic
        topic = intent.getStringExtra("TOPIC_TITLE").toString();
        ( (TextView) findViewById(R.id.topic_detail)).setText(topic);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void create_discussion(View view){

        // Extract form data and create a new discussion via the discussion manager
        String disc_title = ((EditText) findViewById(R.id.discussion_title_entry)).getText().toString();
        String disc_content = ((EditText) findViewById(R.id.discussion_content_entry)).getText().toString();

        // Create new discussion
        DiscussionManager d_manager = new DiscussionManager(true);
        TopicManager tm = new TopicManager(true);

        // Add the date with the proper fields
        // everything else i.e user and date will be set within the manager
        d_manager.add( new Discussion(tm.get(topic), disc_title, disc_content, new User(), "2022-04-1") );

        // Navigate back to the discussion list of topic
        Intent nav = new Intent(this, DiscussionListActivity.class);
        nav.putExtra("TOPIC_TITLE", topic);
        startActivity(nav);
    }
}