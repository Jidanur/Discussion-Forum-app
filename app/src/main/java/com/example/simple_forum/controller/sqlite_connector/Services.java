package com.example.simple_forum.controller.sqlite_connector;

public class Services {

    private static ITopicPersistence itp = null;

    public static synchronized ITopicPersistence getTopicPersistence()
    {
        if (itp == null)
        {

            itp = new TopicPersistence(Main.get_dbManager());
        }

        return itp;
    }
}
