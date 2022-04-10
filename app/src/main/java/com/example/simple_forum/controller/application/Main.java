package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static String dbName = "SF";

    public static void main(String[] args){

        // Access DB locally not within the emulator
        // Make sure to set db path name to its relative path before accessing
        setDBPathName("app/src/main/assets/db/SF");
        TopicManager tm = new TopicManager(true);
        System.out.println(tm.get_list());

    }

    public static void setDBPathName(final String name) {
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}

