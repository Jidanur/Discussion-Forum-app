package com.example.simple_forum.controller.sqlite_connector;

import com.example.simple_forum.models.Topic;

import java.util.ArrayList;

public interface ITopicPersistence {

    void add_Topic(Topic t);
    ArrayList<Topic> get_TopicList(String data);
}
