package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.Topic;
import java.util.ArrayList;

public interface ITopicPersistence {
    // Insert a topic into the database
    public void insert_topic(Topic t);

    // Delete a topic from the db
    public void delete_topic(Topic t);

    // Get a certain topic
    public Topic get(String title);

    // Get all topics in the DB
    public ArrayList<Topic> get_all();

    // Get the count of all the rows
    public int get_count();
}
