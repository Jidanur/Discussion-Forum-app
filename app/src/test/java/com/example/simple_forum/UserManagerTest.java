package com.example.simple_forum;

import static org.junit.Assert.*;
import com.example.simple_forum.controller.managers.UserManager;

import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Test;

import java.util.ArrayList;

public class UserManagerTest {

    @Test
    public void getList_Test(){
        UserManager test = new UserManager();

        //Test if the method return a list.
        assertNotNull(test.get_list());
        assertEquals(0,test.get_list().size());
    }

    @Test
    public void addTest(){

        // Instantiate a topic manager.
        UserManager t_manager = new UserManager();

        // Create new user with data
        User t_user = new User("haha", "abc123","haha@hotmail.com","");
        t_manager.add(t_user);

        // Check to see if it has been added.
        assertEquals(1, t_manager.size());
    }

    @Test
    public void getUserByPosition_Test(){
        UserManager test = new UserManager();

        // Clear UserManager first
        test.clear();

        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");
        test.add(testUser1);
        test.add(testUser2);

        // Test if the method find the correct object.
        assertEquals(testUser1,test.get(0));
        assertEquals(testUser2,test.get(1));
    }

    @Test
    public void getUserByObjectTest(){
        UserManager test = new UserManager();

        // Clear UserManager first
        test.clear();

        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");
        test.add(testUser1);
        test.add(testUser2);

        // Test if the method find the correct object.
        assertEquals(testUser1,test.get(testUser1));
        assertEquals(testUser2,test.get(testUser2));
    }

    @Test
    public void getSize_Test(){
        UserManager test = new UserManager();

        test.clear();
        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");

        test.add(testUser1);
        test.add(testUser2);

        //Test the size after users were added into UserManager.
        assertEquals(2,test.size());
    }

    @Test
    public void get_listTest(){
        UserManager test1 = new UserManager();
        ArrayList<User> test2 = new ArrayList<User>();

        test1.clear();
        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");

        test1.add(testUser1);
        test1.add(testUser2);
        test2.add(testUser1);
        test2.add(testUser2);

        // test the size of list
        assertEquals(2,test1.get_list().size());

        // test if it return the correct list
        assertEquals(test2,test1.get_list());
    }

    @Test
    public void existsTest(){
        UserManager test = new UserManager();

        test.clear();
        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");

        test.add(testUser1);
        test.add(testUser2);

        // test if it can find the correct user
        assertEquals(true,test.exists("kurt"));
        assertEquals(true,test.exists("Jiale"));
        assertEquals(false,test.exists("aaa"));
    }

    @Test
    public void clearTest(){
        UserManager test = new UserManager();

        User testUser1 = new User("kurt","abcd123","kurt@uofm.ca","");
        User testUser2 = new User("Jiale","abcd456","Jiale@uofm.ca","");

        test.add(testUser1);
        test.add(testUser2);

        assertEquals(2,test.size());

        test.clear();
        // test it does clear the userList
        assertEquals(0,test.size());
    }



}