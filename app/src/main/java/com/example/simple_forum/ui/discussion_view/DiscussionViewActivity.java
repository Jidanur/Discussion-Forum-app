package com.example.simple_forum.ui.discussion_view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class DiscussionViewActivity extends AppCompatActivity {

    private String topic_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_view);

        TextView titleTxt = findViewById(R.id.discussionView_title);
        TextView contentTxt = findViewById(R.id.discussionView_content);
        TextView usernameTxt = findViewById(R.id.discussionView_username);
        TextView dateTxt = findViewById(R.id.discussionView_date);

        String disc_title = "Title not set";
        String disc_content = "Content not set";
        String disc_username = "Username not set";
        String disc_date = "Date not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            topic_title = extras.getString("topic title");
            disc_title = extras.getString("discussion title");
            disc_content = extras.getString("discussion content");
            disc_username = extras.getString("discussion username");
            disc_date = extras.getString("discussion date");
        }

        titleTxt.setText(disc_title);
        contentTxt.setText(disc_content);
        usernameTxt.setText(disc_username);
        dateTxt.setText(disc_date);
    }

    // Clicked back to discussion list
    public void back_to_disc_list(View view){

        // Start intent
        Intent disc_list = new Intent(this, DiscussionListActivity.class);
        disc_list.putExtra("TOPIC_TITLE", topic_title);
        startActivity(disc_list);
    }
}