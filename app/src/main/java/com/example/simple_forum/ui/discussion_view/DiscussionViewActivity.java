package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.controller.managers.DiscussionManager;
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

        // Use local DB
        if(Main.get_local_setting()){

            com_manager = new CommentManager(Main.get_local_setting());

            // Set recycler
            com_recycler = findViewById(R.id.comments_list);

            // Set adapter
            set_adapter();

        } else {
            // Exec async http calls
            new AsyncCaller().execute();
        }

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
    }

    // Create comment


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

        Intent disc_list = new Intent(DiscussionViewActivity.this, DiscussionListActivity.class);
        disc_list.putExtra("topic", topic);
        startActivity(disc_list);
    }

    protected class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Notify
            Toast.makeText(getApplicationContext(), "Getting data from server", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //Get comment entries for this discussion
            com_manager = new CommentManager(Main.get_local_setting());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Set recycler
            com_recycler = findViewById(R.id.comments_list);

            // Set adapter
            set_adapter();
        }
    }
}