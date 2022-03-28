package com.example.simple_forum.controller.sqlite_connector;

public class Main {
    public static void main(String[] args){

        TopicPersistence tp = new TopicPersistence();

        tp.create_db("SIMPLE_FORUM_DB.db");
        tp.create_tables();
    }
}
