package com.example.simple_forum.ui.startup_page;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import com.example.simple_forum.R;
import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.IHTTPUtils;
import com.example.simple_forum.controller.persistence.Utils;
import com.example.simple_forum.ui.login_view.LoginActivity;
import com.example.simple_forum.ui.topic_view.TopicListActivity;

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

        // Set local to true if server is unavailable
        if( !new HttpUtils().get_server_status() || Main.get_local_setting() ) {
            Main.set_local_setting(true);
            System.out.println("USING HSQLDB BASED PERSISTENCE");
        } else{
            Main.set_local_setting(false);
            System.out.println("USING HTTP BASED PERSISTENCE");
        }

        // Go to login page
        Intent login_page = new Intent(StartUpActivity.this, LoginActivity.class);
        startActivity(login_page);
    }
}