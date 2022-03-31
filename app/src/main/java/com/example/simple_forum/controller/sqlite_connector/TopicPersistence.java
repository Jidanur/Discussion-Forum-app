package com.example.simple_forum.controller.sqlite_connector;

import com.example.simple_forum.models.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TopicPersistence {

    private final IDBManager idbManager;

    public TopicPersistence(IDBManager db) {
        this.idbManager = db;
        db.connect();
        db.initialize();
    }

    public void add_topic(Topic t){
        Statement stmt;

        if(idbManager.connect()) {
            try{
                stmt  = idbManager.getConnection().createStatement();

                String username  = t.getUser().getUsername();

                String statement = "SELECT id FROM user WHERE username = " + username;

                ResultSet rs = stmt.executeQuery(statement);
                int user = rs.getInt("id");

                String columns = "title, date_created, user";
                String[] data = {t.getTitle(), t.getDate_created().toString(), String.valueOf(user)};

                idbManager.insert(columns, data, "topic");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Topic get_Topic(String data){

        return null;
    }
}
