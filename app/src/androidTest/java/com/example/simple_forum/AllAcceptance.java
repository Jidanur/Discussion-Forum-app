

package com.example.simple_forum;

import com.example.simple_forum.acceptance_test.CreateCommentTest;
import com.example.simple_forum.acceptance_test.CreateDiscussionTest;
import com.example.simple_forum.acceptance_test.CreateNewTopicTest;
import com.example.simple_forum.acceptance_test.LoginTest;
import com.example.simple_forum.acceptance_test.TopicAndDiscussionViewTest;
import com.example.simple_forum.acceptance_test.UserRegisterTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        UserRegisterTest.class,
        CreateNewTopicTest.class,
        CreateDiscussionTest.class,
        TopicAndDiscussionViewTest.class,
        CreateCommentTest.class
})
public class AllAcceptance {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllAcceptance.class);
        System.out.println(result.wasSuccessful());
    }
}
