package com.example.simple_forum.ui.discussion_view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.ui.adapters.CommentRecyclerAdapter;

public class DiscussionViewActivity extends AppCompatActivity {

    private CommentManager com_manager = new CommentManager();
    private Topic topic;
    private RecyclerView com_recycler;
    private CommentRecyclerAdapter com_adapter;
    private Discussion disc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion_view);

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

        // Set recycler
        com_recycler = findViewById(R.id.comments_list);

        // Use local DB
        if(Main.get_local_setting()){

            com_manager = new CommentManager(Main.get_local_setting());

        } else {
            // Exec async http calls
            new AsyncCaller().execute();
        }

        // Set adapter
        set_adapter();
    }

    // Create comment
    public void create_comment(View view){

        // Use local DB to insert
        if(Main.get_local_setting()){
            // Grab the comment text from the field
            String comment_content = ((EditText) findViewById(R.id.comment_text_edit)).getText().toString();

            // Create the comment
            Comment c = new Comment(disc, comment_content, null, "");

            // Add it
            com_manager.add(c);

            // Notify adapter change
            com_adapter.notifyDataSetChanged();
        } else {
            // Exec async http calls
            new AsyncPOSTCaller().execute();
        }

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

        Intent disc_list = new Intent(DiscussionViewActivity.this, DiscussionListActivity.class);
        disc_list.putExtra("topic", topic);
        startActivity(disc_list);
    }

    protected class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Notify
            Toast.makeText(getApplicationContext(), "Loading comments", Toast.LENGTH_LONG).show();
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

            // Notify
            com_adapter = new CommentRecyclerAdapter(com_manager, disc.getTitle());
            com_recycler.setAdapter(com_adapter);

            Toast.makeText(getApplicationContext(), "Comments loaded", Toast.LENGTH_SHORT).show();
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

            // Grab the comment text from the field
            String comment_content = ((EditText) findViewById(R.id.comment_text_edit)).getText().toString();

            // Create the comment
            Comment c = new Comment(disc, comment_content, null, "");

            // Add it
            com_manager.add(c);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Notify adapter change
            com_adapter = new CommentRecyclerAdapter(com_manager, disc.getTitle());
            com_recycler.setAdapter(com_adapter);

            Toast.makeText(getApplicationContext(),"Data retrieved",Toast.LENGTH_SHORT).show();
        }
    }
}