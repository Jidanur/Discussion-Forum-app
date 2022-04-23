package com.example.simple_forum.ui.topic_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.ui.adapters.TopicRecyclerAdapter;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;
import com.example.simple_forum.ui.discussion_view.DiscussionListActivity;

public class TopicListActivity extends AppCompatActivity implements TopicRecyclerAdapter.OnTopicListener {

    private static TopicManager t_manager = new TopicManager();
    private RecyclerView topic_recycler;
    private TopicRecyclerAdapter topic_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);

        topic_recycler = findViewById(R.id.topic_list);

        // If we are using local
        if(Main.get_local_setting()){
            t_manager = new TopicManager(Main.get_local_setting());

            set_adapter();
        } else {
            // Use HTTP async calls
            new AsyncCaller().execute();
        }

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

        // If we are using local
        if(Main.get_local_setting()){

            // Get the text from the input field
            EditText text_input = (EditText) findViewById(R.id.topic_create_input);
            String new_topic = text_input.getText().toString();

            // Create a new topic object
            Topic t = new Topic(new_topic, new User(), "");
            t_manager.add(t);

            // Notify the adapter of the change
            topic_adapter.notifyDataSetChanged();
        } else {
            // Use HTTP async calls
            new AsyncPOSTCaller().execute();
        }

    }

    @Override
    public void onTopicClick(int position) {

        // Start a new intent and pass in the Topic object
        Intent discussion_list = new Intent(TopicListActivity.this, DiscussionListActivity.class);

        // Pass the topic title as an extra arg to the activity
        Topic t = t_manager.get(position);
        Log.d("TOPIC_LIST", "onTopicClick: " + t.getTitle());
        discussion_list.putExtra("topic", t);
        startActivity(discussion_list);
    }

    protected class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Getting data from server",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // Start the manager
            t_manager = new TopicManager(Main.get_local_setting());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Set the adapter for the recycler
            set_adapter();

            Toast.makeText(getApplicationContext(),"Data retrieved",Toast.LENGTH_SHORT).show();
        }
    }

    protected class AsyncPOSTCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Sending data to server",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // Get the text from the input field
            EditText text_input = (EditText) findViewById(R.id.topic_create_input);
            String new_topic = text_input.getText().toString();

            // Create a new topic object
            Topic t = new Topic(new_topic, new User(), "");

            // Add the topic to the topic manager
            t_manager.add(t);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Notify the adapter of the change
            topic_adapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(),"Data retrieved",Toast.LENGTH_SHORT).show();
        }
    }
}
