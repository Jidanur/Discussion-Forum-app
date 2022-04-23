package com.example.simple_forum.controller.http_connector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils implements IHTTPUtils{


    private static final Map<SF_API, String> endpoints = new HashMap<SF_API, String>();
    private final static String base_url = "http://group15simpleforum.pythonanywhere.com";
    private final static String json_tag = "/?format=json";
    private final static int TIMEOUT = 5000;

    public HttpUtils(){

        // Set endpoints
        endpoints.put(SF_API.ADMIN, "/admin");
        endpoints.put(SF_API.TOPICS, "/forum_api/topics");
        endpoints.put(SF_API.DISCUSSIONS, "/forum_api/discussions");
        endpoints.put(SF_API.COMMENTS, "/forum_api/comments");
        endpoints.put(SF_API.USERS, "/user_profile_api/users");
        endpoints.put(SF_API.USER_PROFILES, "/user_profile_api/user_profiles");
        endpoints.put(SF_API.TOKEN_AUTH, "/api_token_auth");
    }

    @Override
    public boolean get_server_status() {

        return get_endpoint_status(SF_API.ADMIN);
    }

    @Override
    public boolean get_endpoint_status(SF_API ep) {
        try {
            // Try a get request to the endpoint from the server
            String spec = base_url + endpoints.get(ep);
            URL url = new URL(spec);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

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
            con.setRequestMethod("GET");
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

            // Read content if status code is ok
            int status = con.getResponseCode();
            System.out.println("GOT RESPONSE CODE FROM ENDPOINT " + endpoint.name() + " " + status);
            if(status == HttpURLConnection.HTTP_OK){
                data = IHTTPUtils.get_content(con);
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

        try{
            // Connection set up
            String spec = base_url + endpoints.get(endpoint) + json_tag;
            URL url = new URL(spec);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(data.toString().getBytes().length));
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

            // Write the output stream
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data.toString());
            wr.close();

            // Read content if status code is ok
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED){
                System.out.println("GOT RESPONSE: " + status);
                con.disconnect();
                return true;
            } else {
                System.out.println("RECIEVED STATUS CODE FROM ENDPOINT " + endpoint.toString() + " " + status);
                System.out.println("REQUEST BODY: " + data);
                con.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean auth(String username, String password) {
        try{
            // Connection set up
            JSONObject data = new JSONObject();
            data.put("username", username);
            data.put("password", password);
            String spec = base_url + endpoints.get(SF_API.TOKEN_AUTH) + json_tag;
            URL url = new URL(spec);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(data.toString().getBytes().length));
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setConnectTimeout(TIMEOUT);
            con.setReadTimeout(TIMEOUT);

            // Write the output stream
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data.toString());
            wr.close();

            // Read content if status code is ok
            int status = con.getResponseCode();
            System.out.println("RECIEVED STATUS CODE FROM ENDPOINT " + SF_API.TOKEN_AUTH.name() + " " + status);
            if(status == HttpURLConnection.HTTP_OK){
                con.disconnect();
                return true;
            } else {
               con.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
