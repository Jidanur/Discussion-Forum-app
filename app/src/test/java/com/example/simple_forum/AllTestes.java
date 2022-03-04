package com.example.simple_forum.testRunner;

import com.example.simple_forum.JSONPArserTest;
import com.example.simple_forum.TopicManagerTest;
import com.example.simple_forum.controller.adapters.TopicRecyclerAdapterTest;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.data.model.LoggedInUserTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TopicRecyclerAdapterTest.class,
        TopicManagerTest.class,
        LoggedInUserTest.class
})
public class AllTestes {
}
