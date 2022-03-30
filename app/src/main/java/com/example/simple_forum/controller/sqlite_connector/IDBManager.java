package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;

public interface IDBManager {

    boolean connect();
    void initialize();
    boolean insert(String columns, String[] data, String table);
    String  get_Data(String data);
    boolean disconnect();
    Connection getConnection();
}
