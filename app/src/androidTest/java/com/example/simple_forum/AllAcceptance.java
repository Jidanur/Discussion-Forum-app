

package com.example.simple_forum;

import com.example.simple_forum.acceptance_test.LoginTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
})
public class AllAcceptance {
    public static void main(String[] args){
        Result result = JUnitCore.runClasses(AllAcceptance.class);
        System.out.println(result.wasSuccessful());
    }
}
