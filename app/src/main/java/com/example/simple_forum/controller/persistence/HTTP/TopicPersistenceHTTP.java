package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.models.Topic;

import java.util.ArrayList;

public class TopicPersistenceHTTP implements ITopicPersistence {

    public TopicPersistenceHTTP(){

    }

    public boolean check_server_status(){
        // TODO
        // Ping server
        return false;
    }

    // Insert topic via API to server
    @Override
    public void insert_topic(Topic t) {

    }

    @Override
    public void delete_topic(Topic t) {

    }

    @Override
    public Topic get(String title) {
        return null;
    }

    @Override
    public ArrayList<Topic> get_all() {
        return null;
    }
}
