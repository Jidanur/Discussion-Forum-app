package com.example.simple_forum;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Discussion_test {

    @Test
    public void test_discussion_create(){

        Topic topic = new Topic();

        String title = "Good Music";

        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "2022-02-28T00:52:48.769746Z";


        Discussion newDiscussion = new Discussion(null, title,content,new User(),date);

        assertEquals(title,newDiscussion.getTitle());

    }

    @Test
    public void test_addComment(){
        Topic topic = new Topic();
        String title = "Good Music";
        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "2022-02-28T00:52:48.769746Z";


        Discussion newDiscussion = new Discussion(null, title,content,new User(),date);

        //comment to add
        Comment newComment = new Comment(null, "Great",new User(), date);
        newDiscussion.add_comment(newComment);

        ///test
        assertEquals(1,newDiscussion.getComments().size());

    }
    @Test
    public void test_addCommentList(){

        Topic topic = new Topic();
        String title = "Good Music";
        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "2022-02-28T00:52:48.769746Z";

        Discussion newDiscussion = new Discussion(null, title,content,new User(),date);


        //initializing commentList
        ArrayList<Comment> commentList = new ArrayList<>();
        for(int i=0;i<5;i++){
            //commentList.add(new Comment());
            newDiscussion.add_comment(new Comment());
        }

//        newDiscussion.setComment(commentList);
        assertEquals(5,newDiscussion.getComments().size());

    }

    @Test
    public void test_invalid_date(){

        String title = "Good Music";
        String content = "The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now";
        String date = "no date";

        Discussion newDiscussion = new Discussion(null, title,content,new User(),date);

        // date should be null because of invalid date
        assert(newDiscussion.getDate().equals(""));

    }



}
