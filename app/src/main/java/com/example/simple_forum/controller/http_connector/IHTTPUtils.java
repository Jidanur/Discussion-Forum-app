package com.example.simple_forum.controller.http_connector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public interface IHTTPUtils {

    boolean get_server_status();

    boolean get_endpoint_status(SF_API ep);

    JSONArray get(SF_API endpoint);

    boolean post(SF_API endpoint, JSONObject data);

    boolean auth(String username, String password);

    static JSONArray get_content(HttpURLConnection con) throws IOException, JSONException {
        // Get content header
        InputStream is = con.getResponseCode() != 200 ? con.getErrorStream() : con.getInputStream();
        String line = "";
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while( (line = reader.readLine()) != null ){
            content.append(line);
        }
        reader.close();

        // Convert content string to json
        return new JSONArray(content.toString());
    }
}
