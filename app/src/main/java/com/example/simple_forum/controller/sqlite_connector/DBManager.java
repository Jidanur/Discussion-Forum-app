package com.example.simple_forum.controller.sqlite_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager implements IDBManager{

    private static String filename = "";
    private final static String sql_url = "jdbc:sqlite:app\\src\\main\\assets\\";
    private static Connection connection = null;

    public DBManager(String db_name) {

        this.filename = db_name;
    }

    @Override
    public boolean connect() {

        boolean flag = false;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(sql_url + filename);
            flag = true;
        }
        catch(SQLException | ClassNotFoundException e){

        }
        return flag;
    }

    @Override
    public void initialize(){

        Statement stmt;

        if (connect()){

            try {
                stmt = connection.createStatement();

                String create_topic = "CREATE TABLE IF NOT EXISTS topic ("
                        + "id integer PRIMARY KEY AUTOINCREMENT, "
                        + "title varchar(255) NOT NULL UNIQUE, "
                        + "date_created datetime, "
                        + "user int NOT NULL)";

                String create_user = "CREATE TABLE IF NOT EXISTS user ("
                        + "id integer PRIMARY KEY AUTOINCREMENT, "
                        + "username varchar(255) NOT NULL UNIQUE, "
                        + "password varchar(255) NOT NULL, "
                        + "email text NOT NULL unique, "
                        + "bio text(4000))";

                String create_discussion = "CREATE TABLE IF NOT EXISTS discussion ("
                        + "id integer PRIMARY KEY AUTOINCREMENT, "
                        + "title varchar(255) NOT NULL UNIQUE, "
                        + "content text(4000) NOT NULL, "
                        + "user int NOT NULL, "
                        + "date_created datetime, "
                        + "topic int)";

                String create_comment = "CREATE TABLE IF NOT EXISTS comment ("
                        + "id integer PRIMARY KEY AUTOINCREMENT, "
                        + "content text(4000) NOT NULL, "
                        + "user int NOT NULL, "
                        + "date_created datetime, "
                        + "discussion int)";

                stmt.execute(create_topic);
                stmt.execute(create_user);
                stmt.execute(create_discussion);
                stmt.execute(create_comment);

            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean insert(String columns, String[] data, String table){

        boolean flag = false;
        Statement stmt;

        if (connect()) {
            try{
                stmt = connection.createStatement();

                String insert_st = "INSERT INTO" + table + columns + " VAlUES";
            }
            catch(SQLException e){

            }
        }
        return flag;
    }

    @Override
    public Object get() {
        return false;
    }

    @Override
    public boolean disconnect() {

        boolean flag = false;

        if (connect()) {

            try {
                connection.close();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    public Connection getConnection(){
        return connection;
    }
}
