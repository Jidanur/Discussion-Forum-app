package com.example.simple_forum.controller.sqlite_connector;


import com.example.simple_forum.models.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicPersistence implements ITopicPersistence {

    private final IDBManager idbManager = new DBManager("SIMPLE_FORUM_DB.db");

    public TopicPersistence(IDBManager db) {
        idbManager.connect();
        idbManager.initialize();
    }


    @Override
    public void add_Topic(Topic t) {

        if (idbManager.connect()) {
            try {
                //stmt  = idbManager.getConnection().createStatement();

                //String username  = t.getUser().getUsername();

                //String statement = "SELECT id FROM user WHERE username = " + username;

                //ResultSet rs = stmt.executeQuery(statement);
                //int user = rs.getInt("id");

                // Use stub values until users and dates get fixed/implemented properly
                String columns = "title, date_created, user";
                String[] data = {t.getTitle(), "2022-04-01 00:00:00", "1"};

                //idbManager.insert(columns, data, "topic");

                String query = "INSERT INTO topic(title,date_created,user) VALUES(?,?,?)";
                PreparedStatement ps = idbManager.getConnection().prepareStatement(query);
                ps.setString(1, t.getTitle());
                ps.setString(2, "2022-04-01");
                ps.setString(3, "1");

                ps.executeUpdate();

                ps.close();
                idbManager.getConnection().close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ERROR ADD TOPIC PERSISTENCE");
            }
        }
    }

    @Override
    public ArrayList<Topic> get_TopicList() {

        ArrayList<Topic> topicList = new ArrayList<>();

        String fields = "title, date_created, user";
        try {
            if (idbManager.connect()) {

                Statement stmt = idbManager.getConnection().createStatement();

                String statement = "SELECT title, date_created, user FROM topic";
                ResultSet rs = stmt.executeQuery(statement);

                System.out.println("RESULT SET: " + rs.toString());

                while (rs.next()) {
                    Topic t = new Topic();
                    t.setTitle(rs.getString("title"));
                    //t.setDate_created(rs.getDate("date_created"));

                    //to-do
                    // Implement a way to get the user into topic

                    topicList.add(t);
                }

                idbManager.getConnection().close();
                rs.close();
                stmt.close();
            } else {
                System.out.println("NOT CONNECTED");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println(topicList);
        return topicList;
    }
}
