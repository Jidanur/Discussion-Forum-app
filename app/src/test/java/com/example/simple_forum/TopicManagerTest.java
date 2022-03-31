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
    public void getList_Test(){
        TopicManager test = new TopicManager();

        //Test if the method return a list.
        assertNotNull(test.get_list());
        assertEquals(0,test.get_list().size());
    }


    @Test
    public void test_add_topic(){

        // Instantiate a topic manager.
        TopicManager t_manager = new TopicManager();

        // Create new topic with data
        Topic t_comp = new Topic("Movies", new User(), "2022-02-28T00:22:58.538787Z");
        t_manager.add(t_comp);

        // Check to see if it has been added.
        assertEquals(1, t_manager.size());
    }


    @Test
    public void test_json_parser(){

        String test_data = "[ {\"title\": \"Movies\",\"date_created\": \"2022-02-28T00:22:58.538787Z\",\n" +
                "    \"user\": \"kurt\"}]";

        // Instantiate a topic manager
        TopicManager t_manager = new TopicManager();
        t_manager.add_json_file("src/main/assets/topics.json");

        // System.out.println(test_data);
        assertEquals(1,t_manager.size());


        assertEquals("Movies",t_manager.get(0).getTitle());
    }

    @Test
    public void getTopicByTitle_Test(){
        TopicManager test = new TopicManager();

        // Clear topic manager first
        test.clear();

        Topic testTopic1 = new Topic("Movies",new User(),"2022-02-28T00:22:58.538787Z");
        Topic testTopic2 = new Topic("Car",new User(),"2077-02-27T00:22:58.538787Z");
        Topic testTopic3 = null;
        test.add(testTopic1);
        test.add(testTopic2);

        // Test if the method find the correct object.
        assertEquals(test.get("Movies"),testTopic1);
        assertEquals(test.get("Car"),testTopic2);

        // Make sure method return null if the object doesn't in list.
        assertSame(test.get("wow"),testTopic3);
    }

    @Test
    public void getTopicByPosition_Test(){
        TopicManager test = new TopicManager();

        // Clear topic manager first
        test.clear();

        Topic testTopic1 = new Topic("Movies",new User(),"2022-02-28T00:22:58.538787Z");
        Topic testTopic2 = new Topic("Car",new User(),"2077-02-27T00:22:58.538787Z");
        test.add(testTopic1);
        test.add(testTopic2);

        // Test if the method find the correct object.
        assertEquals(test.get(0),testTopic1);
        assertEquals(test.get(1),testTopic2);
    }

    @Test
    public void getSize_Test(){
        TopicManager test = new TopicManager();
        Topic testTopic1 = new Topic("Movies",new User(),"2022-02-28T00:22:58.538787Z");
        Topic testTopic2 = new Topic("Car",new User(),"2077-02-27T00:22:58.538787Z");

        test.add(testTopic1);
        test.add(testTopic2);

        //Test the size after testTopics were added into TopicManager.
        assertEquals(test.size(),2);
    }
}
