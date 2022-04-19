package com.example.simple_forum.controller.http_connector;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IHTTPUtils {

    boolean get_server_status();

    JSONArray get(SF_API endpoint);

    boolean post(SF_API endpoint, JSONObject data);

    String auth(String username, String password);
}
