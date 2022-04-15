package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.CommentPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.TopicPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HTTP.CommentPersistenceHTTP;
import com.example.simple_forum.controller.persistence.HTTP.DiscussionPersistenceHTTP;
import com.example.simple_forum.controller.persistence.HTTP.TopicPersistenceHTTP;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;

public class PersistenceManager {

    // Persistence
    private static ITopicPersistence tp = null;
    private static IDiscussionPersistence dp = null;
    private static ICommentPersistence cp = null;

    // DB Copied?
    private static boolean db_copied = false;

    // Get TopicPersistence type
    public static synchronized ITopicPersistence get_topic_persistence(boolean use_local){

        if(tp != null){ return tp; }

        // Use local if flag set or if server is down
        if(use_local || !(new TopicPersistenceHTTP().check_server_status())){

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
        }

        return tp;
    }

    // Get DiscussionPersistence type
    public static synchronized IDiscussionPersistence get_disc_persistence(boolean use_local){
        if(dp != null){ return dp; }

        // Use local if flag set or if server is down
        if(use_local || !(new TopicPersistenceHTTP().check_server_status())){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !db_copied) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
                db_copied = true;
            }

            dp = new DiscussionPersistenceHSQLDB(Main.getDBPathName());
        } else {

            // Use HTTP
            dp = new DiscussionPersistenceHTTP();
        }

        return dp;
    }

    // Get comment persistence type
    public static synchronized ICommentPersistence get_comment_persistence(boolean use_local){
        if(cp != null){ return cp; }

        // Use local if flag set or if server is down
        if(use_local || !(new TopicPersistenceHTTP().check_server_status())){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !db_copied) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
                db_copied = true;
            }

            cp = new CommentPersistenceHSQLDB(Main.getDBPathName());
        } else {

            // Use HTTP
            cp = new CommentPersistenceHTTP();
        }

        return cp;
    }
}
