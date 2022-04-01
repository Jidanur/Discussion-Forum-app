package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static String dbName = "SF";

    public static void main(String[] args){

        TopicManager topicManager = new TopicManager(true);
        topicManager.add(new Topic("Movies", new User(), "2022-02-28T00:23:06.035252Z"));
        topicManager.add(new Topic("Video Games", new User(), "2022-02-28T00:23:06.035252Z"));
        topicManager.add(new Topic("Cars", new User(), "2022-02-28T00:23:06.035252Z"));
        topicManager.add(new Topic("Popular", new User(), "2022-02-28T00:23:06.035252Z"));
        topicManager.add(new Topic("Music", new User(), "2022-02-28T00:23:06.035252Z"));

        System.out.println(topicManager.get_list().toString());

    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {
        return dbName;
    }

}

