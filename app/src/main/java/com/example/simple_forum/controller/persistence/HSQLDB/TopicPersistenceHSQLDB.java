package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicPersistenceHSQLDB implements ITopicPersistence {

    private final String db_path;

    public TopicPersistenceHSQLDB(String db_path) {
        this.db_path = db_path;
    }

    private Connection connection() throws SQLException{
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:hsqldb:file:" + db_path + ";shutdown=true", "SA", "");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    // Insert topic
    public void insert_topic(Topic t){

        String query = "INSERT INTO topic VALUES(?,?,?,?)";
        TopicManager tm = new TopicManager(true);

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, tm.size()+1);
            statement.setString(2, t.getTitle() );
            // TODO
            // replace placeholder date
            statement.setDate(3, Date.valueOf("2022-04-01"));
            statement.setInt(4, t.getUser().getId());

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Delete a topic from the db
    public void delete_topic(Topic t){
        String query = "DELETE FROM topic WHERE title = ?";

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query) ){

            statement.setString(1, t.getTitle() );
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Topic get(String title){
        String query = "SELECT FROM topic WHERE title = ?";
        Topic t = null;

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            if(rs.next()){
                //TODO
                //Query for user
                t = new Topic(rs.getString("title"), new User(), "2022-04-01");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    // Get all topics in the DB
    public ArrayList<Topic> get_all(){

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

        return queryset;
    }
}
