package com.example.simple_forum.controller.http_connector;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IHTTPUtils {

    public boolean get_server_status();

    public JSONArray get(SF_API endpoint);

    public boolean post(SF_API endpoint, JSONObject data);

    public String auth(String username, String password);
}
