package com.example.simple_forum.controller.sqlite_connector;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static IDBManager dbManager;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args){

        //dbManager = new DBManager("SIMPLE_FORUM_DB.db");


        //ITopicPersistence tp = new TopicPersistence(dbManager);

        //TopicManager topicManager = new TopicManager(true);
//        topicManager.add(new Topic("Movies", new User(), "2022-02-28T00:23:06.035252Z"));
//        topicManager.add(new Topic("Video Games", new User(), "2022-02-28T00:23:06.035252Z"));
//        topicManager.add(new Topic("Cars", new User(), "2022-02-28T00:23:06.035252Z"));
//        topicManager.add(new Topic("Popular", new User(), "2022-02-28T00:23:06.035252Z"));
//        topicManager.add(new Topic("Music", new User(), "2022-02-28T00:23:06.035252Z"));

        //System.out.println(topicManager.get_list().toString());

    }

    public static IDBManager get_dbManager() {
        return dbManager;
    }
}
