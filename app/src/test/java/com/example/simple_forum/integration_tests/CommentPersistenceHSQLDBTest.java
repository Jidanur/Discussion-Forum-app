package com.example.simple_forum.integration_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.CommentPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Before;
import org.junit.Test;

public class CommentPersistenceHSQLDBTest {

    CommentPersistenceHSQLDB cp;

    @Before
    public void init(){
        Main.setDbName("test_comment");
        cp = (CommentPersistenceHSQLDB) PersistenceManager.get_comment_persistence(true,true);
    }

    @Test
    public void test_get_all(){

        // Count from HSQLDB query
        int count_hsql = cp.get_count();

        // Count loaded into user manager from the get_all query
        int count_loaded = cp.get_all().size();

        // If test failed that means not all were loaded or loading failed
        assertTrue(count_hsql == count_loaded && count_hsql != 0);
    }

    @Test
    public void test_insert_comment(){
        // Get current count of rows
        int old_count = cp.get_count();

        // Create a comment
        String content = "goood discussion";
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,"new Discussion","nothing",new User(),"2022-02-28T00:22:58.538787Z");
        Comment c = new Comment(d,content,new User(),"2022-02-28T00:22:58.538787Z");


        // Insert the user
        cp.insert_comment(c);

        // Get the new count
        int new_count = cp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

    }


    @Test
    public void test_get_comment(){

        Comment c1 = cp.get("good job");
        assertNull(c1);

        // Create a comment
        String content = "goood discussion";
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,"new Discussion","nothing",new User(),"2022-02-28T00:22:58.538787Z");
        Comment c = new Comment(d,content,new User(),"2022-02-28T00:22:58.538787Z");


        // Insert the comment
        cp.insert_comment(c);


        c1 = cp.get(content);
        assertEquals(c1.getContent(),content);


    }


    @Test
    public void test_delete_comment(){
        // Get current count of rows
        int old_count = cp.get_count();

        // Create a comment
        String content = "goood discussion";
        Topic t = new Topic("topic",new User(), "2022-02-28T00:22:58.538787Z");
        Discussion d = new Discussion(t,"new Discussion","nothing",new User(),"2022-02-28T00:22:58.538787Z");
        Comment c = new Comment(d,content,new User(),"2022-02-28T00:22:58.538787Z");


        // Insert the comment
        cp.insert_comment(c);

        /// get new count
        int new_count = cp.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );

        /// delete discussion
        cp.delete_comment(c);

        new_count = cp.get_count();

        /// new count should same as old
        assertTrue(old_count == new_count);



    }






}
