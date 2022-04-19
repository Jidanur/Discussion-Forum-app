package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicPersistenceHSQLDB implements ITopicPersistence {

    private final String db_path;
    private String jdbc_prefix = "jdbc:hsqldb:file:";
    private static UserPersistenceHSQLDB up;

    public TopicPersistenceHSQLDB(String db_path) {

        this.db_path = db_path;
        up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true,true);
    }

    // Use in memory db
    public TopicPersistenceHSQLDB(String db_path, boolean in_memory){
        this.db_path = db_path;
        this.jdbc_prefix = in_memory ? "jdbc:hsqldb:mem" : jdbc_prefix;

        if(in_memory){
            try{
                up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true,true);

                Connection conn = connection();
                Statement st = conn.createStatement();

                // Create and populate table
                st.execute("CREATE MEMORY TABLE PUBLIC.TOPIC(ID INTEGER NOT NULL PRIMARY KEY,TITLE VARCHAR(255) NOT NULL,DATE_CREATED VARCHAR(255),USER INTEGER NOT NULL,UNIQUE(TITLE));");
                st.execute("INSERT INTO TOPIC VALUES(1,'Movies','2022-02-28 00:22:58.000000',1);" +
                        "INSERT INTO TOPIC VALUES(2,'Video Games','2022-02-28 00:23:33.000000',2);" +
                        "INSERT INTO TOPIC VALUES(3,'Cars','2022-02-28 00:23:23.000000',3);" +
                        "INSERT INTO TOPIC VALUES(4,'Popular','2022-02-28 00:23:12.000000',4);" +
                        "INSERT INTO TOPIC VALUES(5,'Music','2022-02-28 00:22:12.000000',5)");

                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private Connection connection() throws SQLException{
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conn = DriverManager.getConnection(jdbc_prefix + db_path + ";shutdown=true", "SA", "");
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

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, get_count()+1);
            statement.setString(2, t.getTitle() );
            statement.setString(3, t.getDate());
            statement.setInt(4, t.getUser().getId());

            statement.executeUpdate();

            // Commit
            c.commit();

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
        String query = "SELECT * FROM topic WHERE title = ?";
        Topic t = null;

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query)){

            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                User u = up.get(rs.getInt("user"));
                t = new Topic(rs.getInt("id"), rs.getString("title"), u, rs.getString("date_created"));
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
        UserManager um = new UserManager(true);

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                // Get the user
                User user = up.get(rs.getInt("user"));

                queryset.add( new Topic(rs.getInt("id"), rs.getString("title"), user, rs.getString("date_created")) );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return queryset;
    }

    @Override
    public int get_count() {
        String query = "SELECT COUNT(*) AS topic_count FROM topic";
        int count = 0;

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){
            rs.next();
            count = rs.getInt("topic_count");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
