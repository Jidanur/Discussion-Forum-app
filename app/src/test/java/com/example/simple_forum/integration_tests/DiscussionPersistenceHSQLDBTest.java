package com.example.simple_forum.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.TopicPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DiscussionPersistenceHSQLDBTest {

    DiscussionPersistenceHSQLDB dp;
    UserPersistenceHSQLDB up;
    TopicPersistenceHSQLDB tp;

    // Stub values
    String stub_date = "2022-02-28T00:22:58.538787Z";
    User stub_user;
    Topic stub_topic;

    @Before
    public void init(){
        Main.setDbName("test_discussion");
        dp = (DiscussionPersistenceHSQLDB) PersistenceManager.get_disc_persistence(true,true);
        up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true,true);
        tp = (TopicPersistenceHSQLDB) PersistenceManager.get_topic_persistence(true,true);

        // Fill stub values with real entries from the in memory db
        stub_user = up.get(1);
        stub_topic = tp.get("Movies");
    }

    @Test
    public void test_get_all(){

        // Count from HSQLDB query
        int count_hsql = dp.get_count();

        // Count loaded into user manager from the get_all query
        int count_loaded = dp.get_all().size();

        // If test failed that means not all were loaded or loading failed
        assertTrue(count_hsql == count_loaded && count_hsql != 0);
    }


    @Test
    public void test_insert_discussion(){

        // Get current count of rows
        int old_count = dp.get_count();

        // Create a Discussion with topic
        Discussion d = new Discussion(stub_topic,"new Discussion","nothing",stub_user,stub_date);

        // Insert the user
        dp.insert_disc(d);

        // Get the new count
        int new_count = dp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );
    }

    @Test
    public void test_get_discussion(){

        /// get on empty database
        Discussion d1 = dp.get("discussion");
        assertNull(d1);

        /// add and get discussion

        // Create a Discussion with topic
        String title = "new Discussion 2";
        Discussion d = new Discussion(stub_topic,title,"nothing",stub_user,stub_date);

        // Insert the user
        dp.insert_disc(d);

        //ArrayList<Discussion> dList = dp.get_all();

        d1 = dp.get(title);
        assertEquals(d1.getTitle(), title);
    }

    @Test
    public void test_delete_discussion(){

        // Get current count of rows
        int old_count = dp.get_count();

        // Create a Discussion with topic
        String title = "new Discussion 3";
        Discussion d = new Discussion(stub_topic,title,"nothing",stub_user,stub_date);

        /// insert
        dp.insert_disc(d);

        /// get new count
        int new_count = dp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

        // Get the inserted discussion with an id
        d = dp.get(title);

        /// delete discussion
        dp.delete_disc(d);

        new_count = dp.get_count();

        /// new count should same as old
        assertTrue(old_count == new_count);
    }
}
