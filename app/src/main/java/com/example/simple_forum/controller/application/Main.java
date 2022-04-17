package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

public class Main {

    private static String dbName = "SF";
    private static String dbPath = "";

    public static void main(String[] args){

        setDBPath("app/src/main/assets/db/testDB");
        UserPersistenceHSQLDB up = new UserPersistenceHSQLDB(getDBPath());
        up.insert_user(new User("test_user", "test_pass123", "test_email@test.com", "test bio"));
        System.out.println(up.get_count());

    }

    public static String getDBName() {
        return dbName;
    }

    public static String getDBPath() { return dbPath; }

    public static void setDBPath(final String path) {
        dbPath = path;
    }

    public static void setDbName(final String name) { dbName = name; }

}

