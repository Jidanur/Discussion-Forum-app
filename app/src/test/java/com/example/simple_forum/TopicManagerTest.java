package com.example.simple_forum;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TopicManagerTest {

    @Test
    public void test_add_topic(){

        // Instantiate a topic manager
        TopicManager t_manager = new TopicManager();

        assertEquals(0, t_manager.size());

        // Create new topic with data
        Topic t_comp = new Topic("Movies", new User(), "2022-02-28T00:22:58.538787Z");
        t_manager.add(t_comp);

        // Check to see if it has been added
        assertEquals(1, t_manager.size());
    }
}