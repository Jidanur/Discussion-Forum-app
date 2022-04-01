package com.example.simple_forum;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.User;

public class User_test {

    String name,pass,email,bio;

    @Before
    public void init(){
         name = "Jidan";
         pass = "12345678";
         email = "jidan@gmail.com";
         bio = "just chill";

    }



    @Test
    public void test_user_create(){
        User newUser = new User(name,pass,email,bio);
        //validating creating of user
        assertNotNull(newUser);
        assertEquals("Jidan",newUser.getUsername());

    }




}
