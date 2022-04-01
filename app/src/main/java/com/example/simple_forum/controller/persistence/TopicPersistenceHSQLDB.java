package com.example.simple_forum.controller.persistence;

import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicPersistenceHSQLDB {

    private final String db_path;

    public TopicPersistenceHSQLDB(String db_path) {
        this.db_path = db_path;
    }

    private Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db_path + ";shutdown=true", "SA", "");
    }

    // Get all topics in the DB
    public ArrayList<Topic> get_topics(){

        ArrayList<Topic> queryset = new ArrayList<Topic>();
        String query = "SELECT * FROM topic";

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){
                // Stub user and date for now
                queryset.add( new Topic(rs.getString("title"), new User(), "2022-04-01") );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
