package com.example.simple_forum.controller.application;
import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;
import com.example.simple_forum.controller.persistence.HSQLDB.DiscussionPersistenceHSQLDB;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.models.Discussion;


public class Main {

    private static String dbName = "SF";
    private static String dbPath = "";

    public static void main(String[] args){

        DiscussionPersistenceHSQLDB dp = (DiscussionPersistenceHSQLDB) PersistenceManager.get_disc_persistence(true, true);
        Discussion d = dp.get("Good Music");

        System.out.println(d.getTitle());
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

