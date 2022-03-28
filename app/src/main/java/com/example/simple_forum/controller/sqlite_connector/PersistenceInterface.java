package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;

public interface PersistenceInterface {

    public Connection connect();
    public boolean create_db(String db_name);
    public void create_table(String table_name);
    public boolean insert(Object item);
}
