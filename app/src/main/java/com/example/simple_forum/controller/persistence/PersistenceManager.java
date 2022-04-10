package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.controller.application.Main;

public class PersistenceManager {

    private static String db_name = Main.getDBPathName();

    // Persistence
    private static ITopicPersistence tp, dp, cp, up = null;

    // Get TopicPersistence type
    public static synchronized ITopicPersistence get_topic_persistence(boolean use_local){

        if(tp != null){ return tp; }

        if(use_local){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context()) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
            }

            tp = new TopicPersistenceHSQLDB(db_name);
        } else {

            // Use HTTP
            tp = new TopicPersistenceHTTP();

            // TODO
            // Test connection to server here
            // If fail, return null
        }

        return tp;
    }
}
