package com.example.simple_forum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.controller.sqlite_connector.DBManager;
import com.example.simple_forum.controller.sqlite_connector.IDBManager;
import com.example.simple_forum.controller.sqlite_connector.TopicPersistence;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DB_integration_test {


    String fileName,extension;
    File tempFile;
    IDBManager test_DB;

    @Before
    public void init(){
        try{
            String fileName = "testDB";
            String extension = ".db";

            File tempFile = File.createTempFile(fileName,extension);

            test_DB = new DBManager(fileName + extension);

        }
        catch (Exception e){
            e.printStackTrace();
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

        TopicPersistence topicP = new TopicPersistence(test_DB);

        User newUser = new User();

        Topic t = new Topic("test",newUser,"date");

        topicP.add_Topic(t);

        ArrayList<Topic> list = topicP.get_TopicList();

    }




}
