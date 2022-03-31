package com.example.simple_forum;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.User;

public class User_test {

    String name = "Jidan";
    String pass = "12345678";
    String email = "jidan@gmail.com";
    String bio = "just chill";
    String date = "2022-02-28T00:22:58.538787Z";

    @Test
    public void test_user_create(){
        User newUser = new User(name,pass,email,bio,date);
        //validating creating of user
        assertNotNull(newUser);
        assertEquals("Jidan",newUser.getUsername());

    }

    @Test
    public void test_invalid_date(){
        //try a invalid date
        date = "no date";

        User newUser = new User(name,pass,email,bio,date);
        assertNull(newUser.getDate_created());
    }




}
