package com.example.simple_forum;

import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.models.Comment;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommentManagerTest {

    @Test
    public void test_add_comment(){
        //initial list
        CommentManager c_manager = new CommentManager();

        //adding a test object
        Comment newComment = new Comment();

        c_manager.add(newComment);

        // if added properly
        assertEquals(1,c_manager.size());

        Comment anotherComment =  new Comment();

        c_manager.add(anotherComment);

        // if added properly
        assertEquals(2,c_manager.size());

        Comment newCom = new Comment();
        c_manager.add(newCom);

        assertEquals(3, c_manager.size());
    }


    @Test
    public void test_json_str(){

        String test_data = "[ {\"discussion\": \"Good Music\", \"content\":\"Not bad, could have been better.\", \"date_created\": \"2022-02-28T00:52:48.769746Z\", \"user\": \"Kurt\"} ]";

        //initial list
        CommentManager c_manager = new CommentManager();

        c_manager.add_json_str(test_data);

        assertEquals(4,c_manager.size());

        Comment c_test = (Comment) c_manager.get(3);
        assertEquals("Not bad, could have been better.",c_test.getContent());

    }
}
