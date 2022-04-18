package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.CommentPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.TopicPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HTTP.CommentPersistenceHTTP;
import com.example.simple_forum.controller.persistence.HTTP.DiscussionPersistenceHTTP;
import com.example.simple_forum.controller.persistence.HTTP.TopicPersistenceHTTP;
import com.example.simple_forum.controller.persistence.HTTP.UserPersistenceHTTP;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.controller.persistence.interfaces.IUserPersistence;

public class PersistenceManager {

    // Persistence
    private static ITopicPersistence tp = null;
    private static IDiscussionPersistence dp = null;
    private static ICommentPersistence cp = null;
    private static IUserPersistence up = null;

    // Get TopicPersistence type
    public static synchronized ITopicPersistence get_topic_persistence(boolean use_local, boolean in_memory){

        if(tp != null){ return tp; }

        // Use local if flag set or if server is down
        if(use_local || in_memory){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !in_memory) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
            }

            tp = in_memory ? new TopicPersistenceHSQLDB(Main.getDBName(), true) : new TopicPersistenceHSQLDB(Main.getDBPath());
        } else {

            // Use HTTP
            tp = new TopicPersistenceHTTP();
        }

        return tp;
    }

    // Get DiscussionPersistence type
    public static synchronized IDiscussionPersistence get_disc_persistence(boolean use_local, boolean in_memory){
        if(dp != null){ return dp; }

        // Use local if flag set or if server is down
        if(use_local || in_memory){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !in_memory) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
            }

            dp = in_memory ? new DiscussionPersistenceHSQLDB(Main.getDBName(), true) : new DiscussionPersistenceHSQLDB(Main.getDBPath());
        } else {

            // Use HTTP
            dp = new DiscussionPersistenceHTTP();
        }

        return dp;
    }

    // Get comment persistence type
    public static synchronized ICommentPersistence get_comment_persistence(boolean use_local, boolean in_memory){
        if(cp != null){ return cp; }

        // Use local if flag set or if server is down
        if(use_local || in_memory ){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !in_memory) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
            }

            cp = in_memory ? new CommentPersistenceHSQLDB(Main.getDBName(), in_memory) : new CommentPersistenceHSQLDB(Main.getDBPath());
        } else {

            // Use HTTP
            cp = new CommentPersistenceHTTP();
        }

        return cp;
    }

    // Get UserPersistence type
    public static synchronized IUserPersistence get_user_persistence(boolean use_local, boolean in_memory){
        if(up != null){ return up; }

        // Use local if flag set or if server is down or db in memory
        if(use_local || in_memory ){

            // Make sure this works outside of the emulator
            Utils u = new Utils();
            if(u.has_context() && !in_memory) {
                // Copy DB instance to the device
                u.copyDatabaseToDevice();
            }

            up = in_memory ? new UserPersistenceHSQLDB(Main.getDBName(), true) : new UserPersistenceHSQLDB(Main.getDBPath());
        } else {

            // Use HTTP
            up = new UserPersistenceHTTP();
        }

        return up;
    }
}
