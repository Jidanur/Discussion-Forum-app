package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.models.Discussion;
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

public class DiscussionPersistenceHSQLDB implements IDiscussionPersistence {

    private final String db_path;

    public DiscussionPersistenceHSQLDB(String db_path) { this.db_path = db_path; }

    private Connection connection() throws SQLException {
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

    @Override
    public void insert_disc(Discussion d) {
        String query = "INSERT INTO discussion VALUES(?,?,?,?,?,?)";
        DiscussionManager dm = new DiscussionManager(true);

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, dm.size()+1);
            statement.setString(2, d.getTitle() );
            statement.setString(2, d.getContent() );
            statement.setDate(4, Date.valueOf("2022-04-01"));
            statement.setInt(5, 1);
            statement.setString(6, d.getTopic().getTitle());

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete_disc(Discussion d) {
        String query = "DELETE FROM discussion WHERE title = ?";

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query) ){

            statement.setString(1, d.getTitle() );
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Discussion get(String title) {
        String query = "SELECT FROM discussion WHERE title = ?";
        Discussion d = null;
        TopicManager tm = new TopicManager(true);

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            if(rs.next()){
                //TODO
                //Query for user
                Topic t = tm.get(rs.getString("topic"));
                d = new Discussion(t, rs.getString("title"), rs.getString("content"), new User(), "2022-04-01");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return d;
    }

    @Override

    public ArrayList<Discussion> get_all() {

        String query = "SELECT * FROM discussion";
        TopicManager tm = new TopicManager(true);
        ArrayList<Discussion> queryset = new ArrayList<Discussion>();

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                // Stub user and date for now
                Topic t = tm.get(rs.getString("topic"));
                queryset.add( new Discussion(t, rs.getString("title"), rs.getString("content"), new User(), "2022-04-01") );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return queryset;
    }
}
