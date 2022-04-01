package com.example.simple_forum.controller.sqlite_connector;

public class Services {

    private static ITopicPersistence itp;

    public static synchronized ITopicPersistence getTopicPersistence()
    {
        if (itp == null)
        {
            itp = new TopicPersistence(Main.getDBPathName());
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
