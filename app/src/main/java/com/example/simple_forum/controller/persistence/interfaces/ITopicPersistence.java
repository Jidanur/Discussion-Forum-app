package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Topic;
import java.util.ArrayList;

public interface ITopicPersistence {
    // Insert a topic into the database
    void insert_topic(Topic t);

    // Delete a topic from the db
    void delete_topic(Topic t);

    // Get a certain topic
    Topic get(String title);
    Topic get(int id);

    // Get all topics in the DB
    ArrayList<Topic> get_all();

    // Get the count of all the rows
    int get_count();
}
