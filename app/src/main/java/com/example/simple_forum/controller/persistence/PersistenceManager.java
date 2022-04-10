package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.controller.application.Main;

public class PersistenceManager {

    // Persistence
    private static ITopicPersistence tp, dp, cp, up = null;

    // DB Copied?
    private static boolean db_copied = false;

    // Get TopicPersistence type
    public static synchronized ITopicPersistence get_topic_persistence(boolean use_local){

        if(tp != null){ return tp; }

        if(use_local){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !db_copied) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
                db_copied = true;
            }

            tp = new TopicPersistenceHSQLDB(Main.getDBPathName());
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
