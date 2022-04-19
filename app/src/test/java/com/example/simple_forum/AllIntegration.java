package com.example.simple_forum;

import com.example.simple_forum.integration_tests.CommentPersistenceHSQLDBTest;
import com.example.simple_forum.integration_tests.DiscussionPersistenceHSQLDBTest;
import com.example.simple_forum.integration_tests.TopicPersistenceHSQLDBTest;
import com.example.simple_forum.integration_tests.UserPersistenceHSQLDBTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        UserPersistenceHSQLDBTest.class,
        DiscussionPersistenceHSQLDBTest.class,
        TopicPersistenceHSQLDBTest.class,
        CommentPersistenceHSQLDBTest.class

})

public class AllIntegration {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllTests.class);
        System.out.println(result.wasSuccessful());
    }
}
