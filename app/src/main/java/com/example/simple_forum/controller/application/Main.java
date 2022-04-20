package com.example.simple_forum.controller.application;

import com.example.simple_forum.controller.http_connector.HttpUtils;

public class Main {

    private static String dbName = "SF";
    private static String dbPath = "";

    // Use HTTP api by default if available
    private static boolean use_local = false;

    public static void main(String[] args){
    }

    public static boolean get_local_setting(){ return use_local; }

    public static String getDBName() {
        return dbName;
    }

    public static String getDBPath() { return dbPath; }

    public static void setDBPath(final String path) {
        dbPath = path;
    }

    public static void setDbName(final String name) { dbName = name; }

    public static void set_local_setting(boolean s){ use_local = s; }
}

