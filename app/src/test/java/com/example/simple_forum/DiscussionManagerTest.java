package com.example.simple_forum;

import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.models.Discussion;

import org.junit.Test;
import static org.junit.Assert.*;

public class DiscussionManagerTest {

    @Test
    public void test_add_discussion(){
        //initial list
        DiscussionManager d_manager = new DiscussionManager();

        //adding a test object
        Discussion newDiscussion = new Discussion();

        d_manager.add(newDiscussion);

        // if added properly
        assertEquals(1,d_manager.size());

        Discussion anotherDiscussion =  new Discussion();

        d_manager.add(anotherDiscussion);

        // if added properly
        assertEquals(2,d_manager.size());


    }


    @Test
    public void test_json_str(){

        String test_data =  "[ {\"title\": \"what is life? \",\"date_created\": \"2022-02-28T00:22:58.538787Z\",\n" +
                " \"user\": \"kurt\" \" ,\"content\": \"life is full of shit, just chill and enjoy till u die\"}] ";


        String test_data_2 = "[\n" +
                "  {\n" +
                "    \"title\": \"Good Music\",\n" +
                "    \"content\": \"The chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now\",\n" +
                "    \"date_created\": \"2022-02-28T00:52:48.769746Z\",\n" +
                "    \"user\": \"kurt\",\n" +
                "    \"topic\": \"Music\"\n" +
                "  }]";

        //initial list
        DiscussionManager d_manager = new DiscussionManager();

        d_manager.add_json_str(test_data_2);

        assertEquals(1,d_manager.size());

        Discussion d_test = (Discussion) d_manager.get(0);
        assertEquals("Good Music",d_test.getTitle());

    }
}
