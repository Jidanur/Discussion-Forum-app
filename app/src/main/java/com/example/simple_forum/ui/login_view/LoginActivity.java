package com.example.simple_forum.ui.login_view;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.simple_forum.R;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.ui.topic_view.TopicListActivity;


public class LoginActivity extends AppCompatActivity {

    private UserManager u_manager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        // Create User manager and parse json test file
        u_manager = new UserManager();

        Button button = (Button) findViewById(R.id.login);
        // Make a login click event
        button.setOnClickListener(new View.OnClickListener() {
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

                // Login user if username and password are not empty
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    Intent topic_list = new Intent(LoginActivity.this, TopicListActivity.class);
                    topic_list.putExtra("username",name);
                    startActivity(topic_list);
                    Toast.makeText(getApplicationContext(),"Welcome, "+name,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}



