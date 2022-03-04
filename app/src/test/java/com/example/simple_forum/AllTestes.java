package com.example.simple_forum;

import com.example.simple_forum.Comment_test;
import com.example.simple_forum.DiscussionManagerTest;
import com.example.simple_forum.Discussion_test;
import com.example.simple_forum.TopicManagerTest;
import com.example.simple_forum.Topic_test;
import com.example.simple_forum.User_test;
import com.example.simple_forum.controller.adapters.TopicRecyclerAdapterTest;
import com.example.simple_forum.data.LoginDataSourceTest;
import com.example.simple_forum.data.model.LoggedInUserTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        TopicManagerTest.class,
        Discussion_test.class,
        User_test.class,
        Topic_test.class,
        Discussion_test.class,
        Comment_test.class,
        LoggedInUserTest.class,
        LoginDataSourceTest.class,
        TopicRecyclerAdapterTest.class
})
public class AllTestes {

    public static void main(String[] args){

        Result result = JUnitCore.runClasses(AllTestes.class);
        System.out.println(result.wasSuccessful());
    }

}
