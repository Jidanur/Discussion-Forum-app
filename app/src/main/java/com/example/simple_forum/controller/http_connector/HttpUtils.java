package com.example.simple_forum.controller.http_connector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils implements IHTTPUtils{


    private static Map<SF_API, String> endpoints = new HashMap<SF_API, String>();
    private final static String base_url = "http://group15simpleforum.pythonanywhere.com";
    private final static String json_tag = "/?format=json";

    public HttpUtils(){

        // Set endpoints
        endpoints.put(SF_API.ADMIN, "/admin");
        endpoints.put(SF_API.TOPICS, "/forum_api/topics");
        endpoints.put(SF_API.DISCUSSIONS, "/forum_api/discussions");
        endpoints.put(SF_API.COMMENTS, "/forum_api/comments");
        endpoints.put(SF_API.USERS, "/user_profile_api/users");
        endpoints.put(SF_API.USER_PROFILES, "/user_profile_api/user_profiles");
    }

    @Override
    public boolean get_server_status() {

        return get_endpoint_status(SF_API.ADMIN);
    }

    @Override
    public boolean get_endpoint_status(SF_API ep) {
        try {
            // Try a get request to the admin endpoint from the server
            String spec = base_url + endpoints.get(ep);
            URL url = new URL(spec);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);

            // Get the response code
            int status = con.getResponseCode();

            System.out.println("Received status code from " + spec + " " + status);

            // Return true for 200
            if (status == HttpURLConnection.HTTP_OK){
                con.disconnect();
                return true;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public JSONArray get(SF_API endpoint) {

        // Try to get the data from an endpoint
        JSONArray data = new JSONArray();
        try{

            // Connection set up
            String spec = base_url + endpoints.get(endpoint) + json_tag;
            URL url = new URL(spec);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            // Read content if status code is ok
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK){
                data = get_content(con);
                con.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean post(SF_API endpoint, JSONObject data) {
        return false;
    }

    @Override
    public String auth(String username, String password) {
        return "";
    }

    private JSONArray get_content(HttpURLConnection con) throws IOException, JSONException {

        // Get content header
        String line = "";
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        while( (line = reader.readLine()) != null ){
            content.append(line);
        }
        reader.close();

        // Convert content string to json
        return new JSONArray(content.toString());
    }
}