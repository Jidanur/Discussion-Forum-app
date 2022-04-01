package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDBManager {

    boolean connect();
    void initialize();
    boolean insert(String columns, String[] data, String table);
    ResultSet get_Data(String data);
    boolean disconnect();
    Connection getConnection();
}
