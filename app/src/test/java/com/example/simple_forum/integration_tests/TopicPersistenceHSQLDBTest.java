package com.example.simple_forum.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.TopicPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Before;
import org.junit.Test;

public class TopicPersistenceHSQLDBTest {


    TopicPersistenceHSQLDB tp;

    @Before
    public void init(){
        Main.setDbName("test_topic");
        tp = (TopicPersistenceHSQLDB) PersistenceManager.get_topic_persistence(true,true);

    }


    @Test
    public void test_get_all(){
        // Count from HSQLDB query
        int count_hsql = tp.get_count();

        // Count loaded into user manager from the get_all query
        int count_loaded = tp.get_all().size();

        // If test failed that means not all were loaded or loading failed
        assertTrue(count_hsql == count_loaded && count_hsql != 0);

    }


    @Test
    public void test_insert_topic(){
        // Get current count of rows
        int old_count = tp.get_count();

        // Create a Discussion with topic
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");

        // Insert the user
        tp.insert_topic(t);

        // Get the new count
        int new_count = tp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

    }



    @Test
    public void test_delete_topic(){
        // Get current count of rows
        int old_count = tp.get_count();

        // Create a  topic
        String title = "topic";
        Topic t = new Topic(title,new User(), "2022-02-28T00:22:58.538787Z");

        /// insert topic
        tp.insert_topic(t);

        /// get new count
        int new_count = tp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

        /// delete topic
        tp.delete_topic(t);

        new_count = tp.get_count();

        /// new count should same as old
        assertTrue(old_count == new_count);

    }


    @Test
    public void test_get_topic(){

        /// trying to get topic which is not in the database
        Topic t1 = tp.get("topic");
        assertNull(t1);


        // Create a  topic
        String title = "topic";
        Topic t = new Topic(title,new User(), "2022-02-28T00:22:58.538787Z");

        /// insert topic
        tp.insert_topic(t);

        // trying to get newly inserted topic
        t1 = tp.get(title);

        assertEquals(t1.getTitle(),title);


    }


}
