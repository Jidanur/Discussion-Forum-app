package com.example.simple_forum.controller.application;
import com.example.simple_forum.controller.http_connector.HttpUtils;
import com.example.simple_forum.controller.http_connector.SF_API;


public class Main {

    private static String dbName = "SF";
    private static String dbPath = "";

    public static void main(String[] args){

        HttpUtils http = new HttpUtils();
        http.get(SF_API.DISCUSSIONS);
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

