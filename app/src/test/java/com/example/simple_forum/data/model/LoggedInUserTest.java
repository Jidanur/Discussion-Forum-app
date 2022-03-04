package com.example.simple_forum.data.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoggedInUserTest {

    // Test getUserId method
    @Test
    public void getUserId_Test(){
        LoggedInUser test1 = new LoggedInUser("123456","apple123");
        LoggedInUser test2 = new LoggedInUser("aaaaaaaa","fasfd@!!!41534153");

        assertEquals("123456",test1.getUserId());
        assertEquals("aaaaaaaa",test2.getUserId());
    }

    // Test getDisplayName method
    @Test
    public void getDisplayName_Test(){
        LoggedInUser test1 = new LoggedInUser("123456","apple123");
        LoggedInUser test2 = new LoggedInUser("aaaaaaaa","fasfd@!!!41534153");

        assertEquals("apple123",test1.getDisplayName());
        assertEquals("fasfd@!!!41534153",test2.getDisplayName());
    }

}