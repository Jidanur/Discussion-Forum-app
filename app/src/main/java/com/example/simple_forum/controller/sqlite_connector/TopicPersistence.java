package com.example.simple_forum.controller.sqlite_connector;


import com.example.simple_forum.models.Topic;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//public class TopicPersistence implements ITopicPersistence {
//
//    private final IDBManager idbManager = new DBManager("SIMPLE_FORUM_DB.db");
//
//    public TopicPersistence(IDBManager db) {
//        idbManager.connect();
//        idbManager.initialize();
//    }
//
//
//    @Override
//    public void add_Topic(Topic t) {
//
//        if (idbManager.connect()) {
//            try {
//                //stmt  = idbManager.getConnection().createStatement();
//
//                //String username  = t.getUser().getUsername();
//
//                //String statement = "SELECT id FROM user WHERE username = " + username;
//
//                //ResultSet rs = stmt.executeQuery(statement);
//                //int user = rs.getInt("id");
//
//                // Use stub values until users and dates get fixed/implemented properly
//                String columns = "title, date_created, user";
//                String[] data = {t.getTitle(), "2022-04-01 00:00:00", "1"};
//
//                //idbManager.insert(columns, data, "topic");
//
//                String query = "INSERT INTO topic(title,date_created,user) VALUES(?,?,?)";
//                PreparedStatement ps = idbManager.getConnection().prepareStatement(query);
//                ps.setString(1, t.getTitle());
//                ps.setString(2, "2022-04-01");
//                ps.setString(3, "1");
//
//                ps.executeUpdate();
//
//                ps.close();
//                idbManager.getConnection().close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println("ERROR ADD TOPIC PERSISTENCE");
//            }
//        }
//    }
//
//    @Override
//    public ArrayList<Topic> get_TopicList() {
//
//        ArrayList<Topic> topicList = new ArrayList<>();
//
//        String fields = "title, date_created, user";
//        try {
//            if (idbManager.connect()) {
//
//                Statement stmt = idbManager.getConnection().createStatement();
//
//                String statement = "SELECT title, date_created, user FROM topic";
//                ResultSet rs = stmt.executeQuery(statement);
//
//                System.out.println("RESULT SET: " + rs.toString());
//
//                while (rs.next()) {
//                    Topic t = new Topic();
//                    t.setTitle(rs.getString("title"));
//                    //t.setDate_created(rs.getDate("date_created"));
//
//                    //to-do
//                    // Implement a way to get the user into topic
//
//                    topicList.add(t);
//                }
//
//                idbManager.getConnection().close();
//                rs.close();
//                stmt.close();
//            } else {
//                System.out.println("NOT CONNECTED");
//            }
//        } catch(SQLException e){
//            e.printStackTrace();
//        }
//        System.out.println(topicList);
//        return topicList;
//    }
//}

public class TopicPersistence implements ITopicPersistence {

    private final String dbPath;

    public TopicPersistence(final String dbPath) {
        this.dbPath = dbPath;
        this.initialize();
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:app\\src\\main\\assets\\" + dbPath ); //+ ";shutdown=true", "SA", "");
    }

    private void initialize() {
        try {
            Statement stmt = connection().createStatement();

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

    private Topic fromResultSet(final ResultSet rs) throws SQLException {
        final String title = rs.getString("title");
        //final String date_created = rs.getString("date_created");

        Topic t = new Topic();
        t.setTitle(title);

        return t;
    }

    @Override
    public ArrayList<Topic> get_TopicList() {
        final ArrayList<Topic> topicList = new ArrayList<>();

        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM topic");

            while (rs.next())
            {
                Topic t = new Topic();
                t.setTitle(rs.getString("title"));

                // Users needs to be implemented
                // date_created needs to be fixed

                topicList.add(t);
            }
            rs.close();
            st.close();

            return topicList;
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void add_Topic(Topic t) {

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO topic(title, date_created, user) VALUES(?, ?, ?)");
            st.setString(1, t.getTitle());

//            // getting the user id from the user table
//            PreparedStatement statement = c.prepareStatement("SELECT id FROM user WHERE username = ?");
//            statement.setString(1, t.getUser().getUsername());
//            ResultSet rs = statement.executeQuery();
//            int user = rs.getInt("id");

            // use stub values until users is properly implemented and date is fixed
            st.setString(2, "2022-03-31 00:00:00");
            st.setString(3, "1");

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
