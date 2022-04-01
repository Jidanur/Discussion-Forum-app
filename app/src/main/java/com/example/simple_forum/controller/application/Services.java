package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.persistence.ITopicPersistence;
import com.example.simple_forum.controller.persistence.TopicPersistenceHSQLDB;

public class Services {

    private static TopicPersistenceHSQLDB topic_persistence = null;

    public static synchronized ITopicPersistence getTopicPersistence() {
        return new TopicPersistenceHSQLDB(Main.getDBPathName());
    }

}
