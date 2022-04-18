package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.persistence.interfaces.BaseHTTPInterface;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.models.Discussion;

import java.util.ArrayList;

public class DiscussionPersistenceHTTP implements IDiscussionPersistence, BaseHTTPInterface {
    @Override
    public void insert_disc(Discussion t) {

    }

    @Override
    public void delete_disc(Discussion t) {

    }

    @Override
    public Discussion get(String title) {
        return null;
    }

    @Override
    public ArrayList<Discussion> get_all() {
        return null;
    }

    @Override
    public int get_count() {
        return 0;
    }

    @Override
    public boolean check_server_status() {
        // TODO
        // Check server status
        return false;
    }
}
