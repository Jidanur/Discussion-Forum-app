package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TopicPersistence implements PersistenceInterface{

    private static String filename = "";
    private final static String sql_url = "jdbc:sqlite:app\\src\\main\\assets\\";
    private static Connection conn = null;

    public boolean connect() {

        try{

            conn = DriverManager.getConnection(sql_url + filename);

            return true;

        } catch (SQLException e){
            return false;
        }
    }

    public boolean create_db(String db_name) {

        this.filename = db_name;

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
    public void create_tables() {

        if (connect()){

            Statement stmt = null;
            try {
                stmt = conn.createStatement();

                String create_topic = "CREATE TABLE IF NOT EXISTS topic (\n"
                        + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                        + "title varchar(255) NOT NULL UNIQUE,\n"
                        + "date_created datetime,\n"
                        + "user int NOT NULL\n"
                        + ")";

                String create_user = "CREATE TABLE IF NOT EXISTS user (\n"
                        + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                        + "username varchar(255) NOT NULL UNIQUE,\n"
                        + "password varchar(255) NOT NULL,\n"
                        + "email text NOT NULL unique,\n"
                        + "bio text(4000)"
                        + ")";

                String create_discussion = "CREATE TABLE IF NOT EXISTS discussion (\n"
                        + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                        + "title varchar(255) NOT NULL UNIQUE,\n"
                        + "content text(4000) NOT NULL,\n"
                        + "user int NOT NULL,\n"
                        + "date_created datetime,"
                        + "topic int"
                        + ")";

                String create_comment = "CREATE TABLE IF NOT EXISTS comment (\n"
                        + "id integer PRIMARY KEY AUTOINCREMENT,\n"
                        + "content text(4000) NOT NULL,\n"
                        + "user int NOT NULL,\n"
                        + "date_created datetime,"
                        + "discussion int"
                        + ")";

                stmt.execute(create_topic);
                stmt.execute(create_user);
                stmt.execute(create_discussion);
                stmt.execute(create_comment);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean insert(Object item) {

        // TODO
        // Determine item type and insert accordingly into the proper table

        return false;
    }
}
