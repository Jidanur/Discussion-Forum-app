package com.example.simple_forum;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import junit.framework.TestCase;


public class Topic_test extends TestCase {

    @Test
    public void test_topic(){
        String date = "2022-02-28T00:52:48.769746Z";
        Topic test_topic = new Topic("Cars", new User(),date);

        assertEquals("Cars",test_topic.getTitle());
    }

    @Test
    public void test_add_discussion(){

        String date = "2022-02-28T00:52:48.769746Z";
        Topic test_topic = new Topic("Cars", new User(),date);

        Discussion d_add = new Discussion();

        test_topic.add_discussion(d_add);

        assertEquals(1,test_topic.getDiscussions().size());
    }

}
