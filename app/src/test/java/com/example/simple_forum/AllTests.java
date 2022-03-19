package com.example.simple_forum;

import com.example.simple_forum.data.LoginDataSourceTest;
import com.example.simple_forum.data.model.LoggedInUserTest;

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
})
public class AllTests {

    public static void main(String[] args){

        Result result = JUnitCore.runClasses(AllTests.class);
        System.out.println(result.wasSuccessful());
    }

}
