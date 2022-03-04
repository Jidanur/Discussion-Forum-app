package com.example.simple_forum;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.User;

public class User_test {

    @Test
    public void test_user_create(){

        User newUser = new User("jidan","12345678","jidan@gmail.com","just chill","2022-02-28T00:22:58.538787Z");

        //validating creating of user
        assertNotNull(newUser);
        assertEquals("jidan",newUser.getUsername());

    }

    @Test
    public void test_invalid_date(){

        User newUser = new User("jidan","12345678","jidan@gmail.com","just chill","no Date");

        assertNull(newUser.getDate_created());
    }






}
