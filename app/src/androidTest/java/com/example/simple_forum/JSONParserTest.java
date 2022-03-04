package com.example.simple_forum;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import android.content.Context;
import org.junit.runner.RunWith;
import com.example.simple_forum.controller.JSONParser;

@RunWith(AndroidJUnit4.class)
public class JSONParserTest {

    Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void JSONArray_getJSONTest(){

        assertNotNull(context);
        assertNotNull(JSONParser.get_json(context,"topics.json"));
    }

}
