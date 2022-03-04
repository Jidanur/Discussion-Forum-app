package com.example.simple_forum.controller.adapters;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.models.Topic;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class TopicRecyclerAdapterTest {

    @Test
    public void getItemCount_Test(){
        TopicManager test_topic_Manager = new TopicManager();
        TopicRecyclerAdapter test_Adapter = new TopicRecyclerAdapter(test_topic_Manager);


        assertEquals(0,test_Adapter.getItemCount());
    }

    public class TopicViewHolderTest {

    }
}

