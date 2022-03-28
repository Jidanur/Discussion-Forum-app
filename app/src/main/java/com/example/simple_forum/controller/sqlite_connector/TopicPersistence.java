package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TopicPersistence implements PersistenceInterface{

    private static String filename = "";
    private final static String sql_url = "jdbc:sqlite:app\\src\\main\\assets\\";
    private static Connection conn = null;

    public Connection connect() {

        try{

            conn = DriverManager.getConnection(sql_url + filename);

            return conn;

        } catch (SQLException e){
            return null;
        }
    }

    public boolean create_db(String db_name) {

        this.filename = filename;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(sql_url + filename);

            if ( conn != null){
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void create_table(String table_name) {
    }

    @Override
    public boolean insert(Object item) {
        return false;
    }
}
