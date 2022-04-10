package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static String dbName = "SF";

    public static void main(String[] args){

    }

    public static void setDBPathName(final String name) {
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}

