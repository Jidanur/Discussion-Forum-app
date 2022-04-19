package com.example.simple_forum.integration_tests;

import static org.junit.Assert.assertTrue;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.CommentPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;

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

}
