package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.ui.adapters.DiscussionRecyclerAdapter;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class DiscussionListActivity extends AppCompatActivity {

    private DiscussionManager disc_manager;
    private String topic;
    private RecyclerView disc_recycler;
    private DiscussionRecyclerAdapter disc_adapter;
    private Intent intent;
    private DiscussionRecyclerAdapter.OnDiscussionListener listener;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_list);

        // Get intent
        intent = getIntent();

        // Set topic
        topic = intent.getStringExtra("TOPIC_TITLE");

        // If we are using local HSQLDB
        if(Main.get_local_setting()){
            disc_manager = new DiscussionManager(Main.get_local_setting());

            // Set the recycler view
            disc_recycler = findViewById(R.id.discussion_list);

            // Set adapter
            set_adapter();
        } else {
            // Exec async for HTTP
            new AsyncCaller().execute();
        }

        // Set the topic title text of the view
        TextView topic_title = (TextView) findViewById(R.id.discussion_topic_title);
        topic_title.setText(topic);
    }

    private void set_adapter() {
        setOnClickListener();

        // Create recycler instance
        disc_adapter = new DiscussionRecyclerAdapter(disc_manager, topic, listener);

        // Create linear layout manger
        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getApplicationContext());

        // Set recyclers layout and default animator
        disc_recycler.setLayoutManager(layout_manager);
        disc_recycler.setItemAnimator(new DefaultItemAnimator());

        // Set the topic recyclers adapter
        disc_recycler.setAdapter(disc_adapter);
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent intent = new Intent(getApplicationContext(), DiscussionViewActivity.class);
            ArrayList<Discussion> queryset = disc_manager.filter(topic);
            Discussion disc_holder = queryset.get(position);

            if (disc_holder != null) {
                intent.putExtra("topic title", topic);
                intent.putExtra("discussion", disc_holder);
            }
            startActivity(intent);
        };
    }

    // Clicked new discussion button
    public void new_discussion(View view) {

        // Open new discussion form
        Intent intent = new Intent(this, NewDiscussionFormActivity.class);

        // Pass extra data
        intent.putExtra("TOPIC_TITLE", topic);

        // Start activity
        startActivity(intent);
    }

    // Clicked back to topics
    public void back_to_topics(View view) {

        // Start intent
        Intent topics_list = new Intent(this, TopicListActivity.class);
        startActivity(topics_list);
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

            // Create a discussion manager using local persistence
            disc_manager = new DiscussionManager(Main.get_local_setting());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Set the recycler view
            disc_recycler = findViewById(R.id.discussion_list);

            // Set adapter
            set_adapter();
            Toast.makeText(getApplicationContext(), "Data retrieved", Toast.LENGTH_SHORT).show();
        }
    }
}
