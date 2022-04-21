package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.ui.adapters.CommentRecyclerAdapter;

public class DiscussionViewActivity extends AppCompatActivity {

    private CommentManager com_manager;
    private Topic topic;
    private RecyclerView com_recycler;
    private CommentRecyclerAdapter com_adapter;
    private Discussion disc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_view);

        //Get comment entries for this discussion
        com_manager = new CommentManager(Main.get_local_setting());

        com_recycler = findViewById(R.id.comments_list);

        TextView titleTxt = findViewById(R.id.discussionView_title);
        TextView contentTxt = findViewById(R.id.discussionView_content);
        TextView usernameTxt = findViewById(R.id.discussionView_username);
        TextView dateTxt = findViewById(R.id.discussionView_date);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            topic = (Topic) extras.get("topic");
            disc = (Discussion) extras.get("discussion");
        }

        if(disc != null) {
            titleTxt.setText(disc.getTitle());
            contentTxt.setText(disc.getContent());
            usernameTxt.setText(disc.getUser().getUsername());
            dateTxt.setText(disc.getDate());
        }
        
        // Set adapter
        set_adapter();
    }

    private void set_adapter() {

        // Create recycler instance
        com_adapter = new CommentRecyclerAdapter(com_manager, disc.getTitle());

        // Create linear layout manger
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        com_recycler.setLayoutManager(layout_manager);
        com_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        com_recycler.setAdapter(com_adapter);
    }

    // Clicked back to discussion list
    public void back_to_disc_list(View view){

        // Start intent
        Intent disc_list = new Intent(this, DiscussionListActivity.class);
        disc_list.putExtra("topic", disc.getTopic());
        startActivity(disc_list);
    }
}