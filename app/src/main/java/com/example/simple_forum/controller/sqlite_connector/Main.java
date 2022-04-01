package com.example.simple_forum.controller.sqlite_connector;

import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static IDBManager dbManager;
    public static void main(String[] args){

        dbManager = new DBManager("SIMPLE_FORUM_DB.db");

        //ITopicPersistence tp = new TopicPersistence(dbManager);

    }

    public static IDBManager get_dbManager() {
        return dbManager;
    }
}
