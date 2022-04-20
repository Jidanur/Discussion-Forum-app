package com.example.simple_forum.ui.startup_page;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.Utils;
import com.example.simple_forum.ui.login_view.LoginActivity;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // INET access
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Set utils app context
        new Utils(getApplicationContext());

        // Set local to false
        Main.set_local_setting(true);

        // Go to login page
        Intent login_page = new Intent(StartUpActivity.this, LoginActivity.class);
        startActivity(login_page);
    }
}