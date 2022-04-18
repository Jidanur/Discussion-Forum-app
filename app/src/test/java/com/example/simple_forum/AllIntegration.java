package com.example.simple_forum;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

})

public class AllIntegration {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllTests.class);
        System.out.println(result.wasSuccessful());
    }
}
