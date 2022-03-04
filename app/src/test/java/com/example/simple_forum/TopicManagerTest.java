package com.example.simple_forum;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TopicManagerTest {

    @Test
    public void test_add_topic(){

        // Instantiate a topic manager
        DiscussionManager t_manager = new DiscussionManager();

        String data = "[\n" +
                "  {\n" +
                "    \"title\": \"Good Music\",\n" +
                "    \"content\": \"The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now\",\n" +
                "    \"date_created\": \"2022-02-28T00:52:48.769746Z\",\n" +
                "    \"user\": \"kurt\",\n" +
                "  }]";

        t_manager.add_json_str(data);

        assertEquals(1, t_manager.size());
    }
}