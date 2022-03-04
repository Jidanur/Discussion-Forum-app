package com.example.simple_forum.controller;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class JSONParser {

    // Parse file
    // Returns a JSON object
    public static JSONArray get_json(Context context, String filename){
        JSONArray str_obj = null;
        try{
            str_obj = new JSONArray( get_json_string(context, filename) );
        } catch (JSONException e){
            Log.i("GuiFormData", "IOException: " + e.getMessage());
        }

        return str_obj;
    }

    // Parse a string
    public static JSONArray get_json(String data){
        JSONArray str_obj = null;
        try{
            str_obj = new JSONArray(data);
        } catch (JSONException e){
            Log.i("GuiFormData", "IOException: " + e.getMessage());
        }

        return str_obj;
    }

    private static String get_json_string(Context context, String filename){

        String data = "";
        try{

            // Get the string as bytes
            InputStream stream = context.getAssets().open(filename);
            int size = stream.available();

            // Read in bytes
            byte[] bytes = new byte[size];
            stream.read(bytes);
            stream.close();

            // Return as str
            data = new String(bytes);

        } catch(IOException e) {
            Log.i("GuiFormData", "IOException: " + e.getMessage());
        }

        return data;
    }

    // TODO
    // Parse string
}
