package com.example.simple_forum;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.User;


public class Comment_test {


    @Test
    public void test_comment(){
        String date = "2022-02-28T00:52:48.769746Z";

        Comment test_comment = new Comment("good topic",new User(),date);

        assertEquals("good topic",test_comment.getContent());

    }

}
