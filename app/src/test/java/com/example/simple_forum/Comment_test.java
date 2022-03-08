package com.example.simple_forum;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.User;


public class Comment_test {


    @Test
    public void test_comment(){

        Discussion discussion = new Discussion();

        String date = "2022-02-28T00:52:48.769746Z";

        Comment test_comment = new Comment(discussion, "good topic", new User(), date);

        assertEquals("good topic",test_comment.getContent());

    }


    @Test
    public void test_comment_edit(){

        Discussion discussion = new Discussion();

        String date = "2022-02-28T00:52:48.769746Z";

        Comment test_comment = new Comment(discussion, "good topic", new User(), date);

        String edited ="I don't agree with the discussion";
        test_comment.set_content(edited);

        assertEquals(edited,test_comment.getContent());
    }


    @Test
    public void test_invalid_date(){

        Discussion discussion = new Discussion();

        String date = "No date";

        Comment test_comment = new Comment(discussion, "good topic", new User(), date);

        assertNull(test_comment.getDate());


    }

}
