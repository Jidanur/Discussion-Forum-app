package com.example.simple_forum.ui.login_view;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.models.User;
import com.example.simple_forum.ui.register_view.RegisterActivity;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class LoginActivity extends AppCompatActivity {

    private UserManager u_manager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        // If we are using local HSQLDB
        if(Main.get_local_setting()){
            u_manager = new UserManager(Main.get_local_setting());
        } else {
            // Exec async for HTTP
            new AsyncCaller().execute();
        }

        // Exec async caller
        new AsyncCaller().execute();

        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.newUser);
        // Make a login click event
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText u_name = (EditText) findViewById(R.id.username);
                EditText p_word = (EditText) findViewById(R.id.password);

                String name = u_name.getText().toString().trim();
                String pwd = p_word.getText().toString().trim();

                // Check if username is empty
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"Please enter your username",Toast.LENGTH_SHORT).show();
                }

                // Check if password is empty
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_SHORT).show();
                }

                // Login user if username and password are correct
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    if((User)getUser() != null) {
                        if (u_manager.auth_user((User) getUser())) {
                            u_manager.set_logged_in_user((User) getUser());
                            Intent topic_list = new Intent(LoginActivity.this, TopicListActivity.class);
                            topic_list.putExtra("username", name);
                            startActivity(topic_list);
                            Toast.makeText(getApplicationContext(), "Welcome, " + name, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

    }

    // get the user by username
    private Object getUser(){
        EditText u_name = (EditText) findViewById(R.id.username);
        String name = u_name.getText().toString().trim();

        Object user = u_manager.get_username(name);

        return user;

    }

    protected class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Create User manager
            u_manager = new UserManager(Main.get_local_setting());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}



