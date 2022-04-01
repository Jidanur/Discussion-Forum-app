package com.example.simple_forum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.controller.persistence.ITopicPersistence;
import com.example.simple_forum.controller.persistence.TopicPersistenceHSQLDB;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DB_integration_test {


    String fileName,extension;
    File tempFile;
    ITopicPersistence db;

    @Before
    public void init(){
        try{
            fileName = "testDB";
            extension = ".script";

           tempFile = File.createTempFile(fileName,extension);

            db = new TopicPersistenceHSQLDB(tempFile.getPath());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("worked");
        }

    }

    @After
    public void clear(){
        try{
            tempFile.delete();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test_add(){

        User newUser = new User();

        Topic t = new Topic("test",newUser,"date");

        db.insert_topic(t);

        ArrayList<Topic> list = db.get_all();

        assertEquals(1,list.size());

    }




}
