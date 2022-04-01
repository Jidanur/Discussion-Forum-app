package com.example.simple_forum.controller.persistence;

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

public class TopicPersistenceHSQLDB implements ITopicPersistence{

    private final String db_path;

    public TopicPersistenceHSQLDB(String db_path) {
        this.db_path = db_path;
    }

    private Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + db_path + ";shutdown=true", "SA", "");
    }

    // Insert topic
    public void insert_topic(Topic t){

        String query = "INSERT INTO topic VALUES(?,?,?)";

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query) ){

            statement.setString(1, t.getTitle() );
            statement.setDate(2, Date.valueOf("2022-04-01"));
            statement.setInt(2, 1);

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
                // Query for user
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
