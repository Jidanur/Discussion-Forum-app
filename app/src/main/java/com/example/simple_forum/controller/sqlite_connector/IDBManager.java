package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;

public interface IDBManager {

    boolean connect();
    void initialize();
    boolean insert(String columns, String[] data, String table);
    Object get();
    boolean disconnect();
    Connection getConnection();
}
