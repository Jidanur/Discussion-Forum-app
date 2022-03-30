package com.example.simple_forum.ui.discussion_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.simple_forum.R;

public class DiscussionView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_view);
        TextView titleTxt = findViewById(R.id.discussionView_title);

        String disc_title = "Title not set";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            disc_title = extras.getString("disc_title");
        }

        titleTxt.setText(disc_title);
    }
}