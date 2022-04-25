package com.example.simple_forum;

import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscussionManagerTest {


    String test_data =  "[ {\"title\": \"what is life? \",\"date_created\": \"2022-02-28T00:22:58.538787Z\",\n" +
            " \"user\": \"kurt\" \" ,\"content\": \"life is full of shit, just chill and enjoy till u die\"}] ";


    String title, content, date,topicTitle;
    User tmpUser;
    Topic tmpTopic;
    DiscussionManager d_manager;
    TopicManager t_manager;

    @Before
    public void init(){ // initial manager
        d_manager = new DiscussionManager();
        title = "Good Music";
        content = "Eminem is the best rapper";
        date = "2022-02-28T00:22:58.538787Z";
        tmpUser = new User();
        topicTitle = "topic";
        tmpTopic = new Topic(topicTitle,tmpUser,date);

        // add some topics to add discussions
        t_manager = new TopicManager();
        t_manager.add(tmpTopic);
    }

    @After
    public void afterTest(){
        d_manager.clear();
        t_manager.clear();
    }

    @Test
    public void test_add_discussion(){

        Discussion d = new Discussion(tmpTopic,title,content,tmpUser,date);
        d_manager.add(d);

        assertEquals(1,d_manager.size());

    }

}
