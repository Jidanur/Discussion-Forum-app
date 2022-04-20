package com.example.simple_forum.ui.register_view;

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
import com.example.simple_forum.models.User;
import com.example.simple_forum.ui.login_view.LoginActivity;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

public class RegisterActivity extends AppCompatActivity{
    private UserManager u_list = new UserManager(true);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);

        Button register = (Button) findViewById(R.id.register);

        // Make a register click event
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(completeText()){
                    u_list.add(makeUser());
                    Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(login);
                }
            }
        });
    }

    // check if user complete the register form
    private boolean completeText(){
        boolean result = false;

        EditText u_name = (EditText) findViewById(R.id.registerUname);
        EditText p_word = (EditText) findViewById(R.id.registerPwd);
        EditText email = (EditText) findViewById(R.id.emailAddrs);
        EditText bio = (EditText) findViewById(R.id.bio);

        String name = u_name.getText().toString().trim();
        String pwd = p_word.getText().toString().trim();
        String ema = email.getText().toString().trim();
        String bi = bio.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(ema) && !TextUtils.isEmpty(bi))
            result = true;
        else
            Toast.makeText(getApplicationContext(),"Please complete the register form",Toast.LENGTH_SHORT).show();

        return result;
    }

    // Make a User object based on data that given by user
    private User makeUser(){
        EditText u_name = (EditText) findViewById(R.id.registerUname);
        EditText p_word = (EditText) findViewById(R.id.registerPwd);
        EditText email = (EditText) findViewById(R.id.emailAddrs);
        EditText bio = (EditText) findViewById(R.id.bio);

        String name = u_name.getText().toString().trim();
        String pwd = p_word.getText().toString().trim();
        String ema = email.getText().toString().trim();
        String bi = bio.getText().toString().trim();

        User user = new User(name,pwd,ema,bi);
        return user;

    }
}
