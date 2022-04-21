package com.example.simple_forum.ui.discussion_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class NewDiscussionFormActivity extends AppCompatActivity {

    private Topic topic;
    private Intent intent;

    private static DiscussionManager d_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_discussion_form);

        // Get intent
        intent = getIntent();

        // Set topic
        topic = (Topic) intent.getExtras().get("topic");
        ( (TextView) findViewById(R.id.topic_detail)).setText(topic.getTitle());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void create_discussion(View view){

        // Extract form data and create a new discussion via the discussion manager
        String disc_title = ((EditText) findViewById(R.id.discussion_title_entry)).getText().toString();
        String disc_content = ((EditText) findViewById(R.id.discussion_content_entry)).getText().toString();

        d_manager = new DiscussionManager(Main.get_local_setting());

        // If we are using local
        if(Main.get_local_setting()){

            // Create new discussion
            d_manager.add(new Discussion(topic, disc_title, disc_content, new User(), ""));

            // Navigate back to the discussion list of topic
            Intent nav = new Intent(NewDiscussionFormActivity.this, DiscussionListActivity.class);
            nav.putExtra("topic", topic);
            startActivity(nav);
        } else {
            // Use HTTP async calls
            new AsyncPOSTCaller().execute();
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

            // Extract form data and create a new discussion via the discussion manager
            String disc_title = ((EditText) findViewById(R.id.discussion_title_entry)).getText().toString();
            String disc_content = ((EditText) findViewById(R.id.discussion_content_entry)).getText().toString();

            // Create a new discussion object
            Discussion d = new Discussion(topic, disc_title, disc_content, new User(), "");

            // Add the discussion to the discussion manager
            d_manager.add(d);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Navigate back to the discussion list of topic
            Intent nav = new Intent(NewDiscussionFormActivity.this, DiscussionListActivity.class);
            nav.putExtra("topic", topic);
            startActivity(nav);
        }
    }
}