package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;

public interface PersistenceInterface {

    public boolean connect();
    public boolean create_db(String db_name);
    public void create_tables();
    public boolean insert(Object item);
}
