package com.example.simple_forum.controller.sqlite_connector;

public class Main {
    public static void main(String[] args){

        IDBManager dbManager = new DBManager("SIMPLE_FORUM_DB.db");

        ITopicPersistence tp = new TopicPersistence(dbManager);

    }
}
