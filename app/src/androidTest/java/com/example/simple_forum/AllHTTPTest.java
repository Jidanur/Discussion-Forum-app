package com.example.simple_forum;

import com.example.simple_forum.http_test.HTTPUtilsTest;
import com.example.simple_forum.http_test.ModelSerializerTest;
import com.example.simple_forum.http_test.TopicPersistenceHTTPTest;
import com.example.simple_forum.http_test.UserPersistenceHTTPTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HTTPUtilsTest.class,
        ModelSerializerTest.class,
        TopicPersistenceHTTPTest.class,
        UserPersistenceHTTPTest.class,
})
public class AllHTTPTest {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllAcceptance.class);
        System.out.println(result.wasSuccessful());
    }
}

