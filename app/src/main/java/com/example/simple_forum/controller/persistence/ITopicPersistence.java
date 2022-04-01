package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.models.Topic;
import java.util.ArrayList;

public interface ITopicPersistence {
    public void insert_topic(Topic t);

    // Delete a topic from the db
    public void delete_topic(Topic t);

    public Topic get(String title);

    // Get all topics in the DB
    public ArrayList<Topic> get_all();
}
