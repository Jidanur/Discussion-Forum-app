package com.example.simple_forum;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.User;

import org.junit.Test;
import static org.junit.Assert.*;

public class Discussion_test {

    @Test
    public void test_discussion_create(){
        String title = "Good Music";

        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "2022-02-28T00:52:48.769746Z";


        Discussion newDiscussion = new Discussion(title,content,new User(),date,null);

        assertEquals(title,newDiscussion.getTitle());

    }

    @Test
    public void test_addComment(){
        String title = "Good Music";
        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "2022-02-28T00:52:48.769746Z";


        Discussion newDiscussion = new Discussion(title,content,new User(),date,null);

        //comment to add
        Comment newComment = new Comment("Great",new User(), date);
        newDiscussion.add_comment(newComment);

        ///test
        assertEquals(1,newDiscussion.getComments().size());

    }

    @Test
    public void test_invalid_date(){

        String title = "Good Music";
        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "no date";

        Discussion newDiscussion = new Discussion(title,content,new User(),date,null);

        assertEquals(null ,newDiscussion.getDate_created());


    }



}
