package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.persistence.ITopicPersistence;
import com.example.simple_forum.controller.persistence.TopicPersistenceHSQLDB;

public class Services {

    private static ITopicPersistence itp;

    public static synchronized ITopicPersistence getTopicPersistence()
    {
        if (itp == null)
        {
            itp = new TopicPersistenceHSQLDB(Main.getDBPathName());
            if (itp == null) {
                System.out.println("Ser: ITP is null");
            }
            else {
                System.out.println("Ser: ITP not null");
            }
        }

        return itp;
    }
}
