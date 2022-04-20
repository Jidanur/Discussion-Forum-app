package com.example.simple_forum.ui.discussion_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class DiscussionViewActivity extends AppCompatActivity {

    private String topic_title;
    private Discussion disc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_view);

        TextView titleTxt = findViewById(R.id.discussionView_title);
        TextView contentTxt = findViewById(R.id.discussionView_content);
        TextView usernameTxt = findViewById(R.id.discussionView_username);
        TextView dateTxt = findViewById(R.id.discussionView_date);

        String disc_title = "Title not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            topic_title = extras.getString("topic title");
            disc = (Discussion) extras.get("discussion");
        }

        if(disc != null) {
            titleTxt.setText(disc.getTitle());
            contentTxt.setText(disc.getContent());
            usernameTxt.setText(disc.getUser().getUsername());
            dateTxt.setText(disc.getDate());
        }
    }

    // Clicked back to discussion list
    public void back_to_disc_list(View view){

        // Start intent
        Intent disc_list = new Intent(this, DiscussionListActivity.class);
        disc_list.putExtra("TOPIC_TITLE", topic_title);
        startActivity(disc_list);
    }
}