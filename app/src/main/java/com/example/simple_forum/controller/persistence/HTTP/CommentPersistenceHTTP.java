package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentPersistenceHTTP implements ICommentPersistence{

    private static HttpUtils http;
    private static SF_API endpoint = SF_API.COMMENTS;

    private static UserPersistenceHTTP up;
    private static DiscussionPersistenceHTTP dp;

    public CommentPersistenceHTTP(){
        http = new HttpUtils();
        up = new UserPersistenceHTTP();
        dp = new DiscussionPersistenceHTTP();
    }

    @Override
    public void insert_comment(Comment c) {
        // Serialize before sending
        http.post(endpoint, c.serialize());
    }

    @Override
    public Comment get(String content) {

        // Iterate and find
        for(Comment c : get_all()){
            if(c.getContent().equals(content)){
                return c;
            }
        }
        return null;
    }

    @Override
    public void delete_comment(Comment c) {
        // Nothing happens here
    }

    @Override
    public ArrayList<Comment> get_all() {

        // Result list
        ArrayList<Comment> comments_list = new ArrayList<>();

        // Get all items as json from the endpoint
        JSONArray topics = http.get(endpoint);

        // Convert from json objects to comment objects
        for(int i = 0; i < topics.length(); i++){
            try {

                // Extract key value pairs
                JSONObject obj = topics.getJSONObject(i);
                int id = obj.getInt("id");
                String content = obj.getString("content");
                String date_created = obj.getString("date_created");
                User u = up.get(obj.getInt("user"));
                Discussion d = dp.get(obj.getInt("discussion"));

                // Build and add new comment
                Comment c = new Comment(id, d, content, u, date_created);
                comments_list.add(c);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return comments_list;
    }

    @Override
    public int get_count() {
        return http.get(endpoint).length();
    }
}
