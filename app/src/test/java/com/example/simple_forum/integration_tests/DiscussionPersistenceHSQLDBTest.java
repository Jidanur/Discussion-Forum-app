package com.example.simple_forum.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DiscussionPersistenceHSQLDBTest {


    DiscussionPersistenceHSQLDB dp;

    @Before
    public void init(){
        Main.setDbName("test_discussion");
        dp = (DiscussionPersistenceHSQLDB) PersistenceManager.get_disc_persistence(true,true);
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
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,"new Discussion","nothing",new User(),"2022-02-28T00:22:58.538787Z");

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
        String title = "new Discussion";
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,title,"nothing",new User(),"2022-02-28T00:22:58.538787Z");

        // Insert the user
        dp.insert_disc(d);

        //ArrayList<Discussion> dList = dp.get_all();

        d1 = dp.get(title);
        //assertEquals(d1.getTitle(), title);


    }

    @Test
    public void test_delete_discussion(){

        // Get current count of rows
        int old_count = dp.get_count();

        // Create a Discussion with topic
        String title = "new Discussion";
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,title,"nothing",new User(),"2022-02-28T00:22:58.538787Z");

        /// insert
        dp.insert_disc(d);

        /// get new count
        int new_count = dp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

        /// delete discussion
        dp.delete_disc(d);

        new_count = dp.get_count();

        /// new count should same as old
        assertTrue(old_count == new_count);



    }



}
