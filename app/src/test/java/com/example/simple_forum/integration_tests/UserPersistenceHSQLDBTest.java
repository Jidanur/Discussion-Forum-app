package com.example.simple_forum.integration_tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.User;


public class UserPersistenceHSQLDBTest {

    UserPersistenceHSQLDB up;

    @Before
    public void init(){

        Main.setDbName("test_user");
        up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true, true);
    }

    @Test
    public void test_get_all(){

        // Count from HSQLDB query
        int count_hsql = up.get_count();

        // Count loaded into user manager from the get_all query
        int count_loaded = up.get_all().size();

        // If test failed that means not all were loaded or loading failed
        assertTrue(count_hsql == count_loaded && count_hsql != 0);
    }

    @Test
    public void test_insert_user(){

        // Get current count of rows
        int old_count = up.get_count();

        // Create a new user with no ID
        User u = new User("test_user", "test_pass123", "test_email@test.com", "test bio");

        // Insert the user
        up.insert_user(u);

        // Get the new count
        int new_count = up.get_count();

        // new count should be greater than old count by only 1
        assertTrue( old_count < new_count && new_count < old_count + 2 );
    }

    @Test
    public void test_get_user(){
        User u = up.get("kurt");
        User u2 = new User("kurt", "kurt123", "kurt@uofm.com", "This is my bio!");
        assertTrue(u != null & u.equals(u2));
    }

    @Test
    public void test_delete_user(){

        // Get current count of rows
        int old_count = up.get_count();

        // Create a new user with no ID
        User u = new User("test_user1", "test_pass1234", "test_email1@test.com", "test bio 1");

        // Insert the user
        up.insert_user(u);

        // Get the new count
        int new_count = up.get_count();

        // new count should be greater than old count by only 1
        assertTrue( "OLD: " + old_count + " | NEW COUNT: " + new_count, old_count < new_count && new_count < old_count + 2 );

        // Delete the user
        up.delete_user(u);

        new_count = up.get_count();

        // new count should be the same as the old count
        assertTrue("OLD: " + old_count + " | NEW COUNT: " + new_count, old_count == new_count);
    }
}
