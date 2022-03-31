package com.example.simple_forum;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.User;

public class User_test {

    @Test
    public void test_user_create(){

        User newUser = new User("jidan","12345678","jidan@gmail.com","just chill");

        //validating creating of user
        assertNotNull(newUser);
        assertEquals("jidan",newUser.getUsername());

    }
}
