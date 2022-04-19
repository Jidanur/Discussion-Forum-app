package com.example.simple_forum.controller.application;

public class Main {

    private static String dbName = "SF";
    private static String dbPath = "";

    public static void main(String[] args){
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

