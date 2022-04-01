package com.example.simple_forum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.simple_forum.controller.application.Main;
import com.example.simple_forum.controller.application.Services;
import com.example.simple_forum.controller.persistence.ITopicPersistence;
import com.example.simple_forum.controller.persistence.TopicPersistenceHSQLDB;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DB_integration_test {


    String fileName;
    Path tempFile;
    ITopicPersistence db;
    Topic t;

    @Before
    public void init(){

        User newUser = new User();
        fileName = "\\app\\src\\main\\assets\\db\\testDB";
        db = new TopicPersistenceHSQLDB(fileName);
        t = new Topic("test",newUser,"2022-04-01");

    }

    @After
    public void clear(){

        Topic get = db.get(t.getTitle());
        if(get != null){
            db.delete_topic(t);
        }

    }


    @Test
    public void test_add(){

        db.insert_topic(t);

        ArrayList<Topic> list = db.get_all();

        assertEquals(1,list.size());

    }

    @Test
    public void test_delete(){

        db.insert_topic(t);

        ArrayList<Topic> list = db.get_all();

        assertEquals(1,list.size());

        db.delete_topic(t);

        assertEquals(0,list.size());

    }

    @Test
    public void test_get(){

        db.insert_topic(t);

        ArrayList<Topic> list = db.get_all();

        assertEquals(1,list.size());


        Topic get = db.get(t.getTitle());

        assertNotNull(get);
    }
}
