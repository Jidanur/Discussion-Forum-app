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


        //initial list
        DiscussionManager d_manager = new DiscussionManager();

        d_manager.add_json_str(test_data);

        assertEquals(1,d_manager.size());

        Discussion d_test = (Discussion) d_manager.get(0);
        //assertEquals("what is life?",d_test.getTitle());

    }
}
