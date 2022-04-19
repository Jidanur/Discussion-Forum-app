package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentPersistenceHSQLDB implements ICommentPersistence {

    private final String db_path;
    private String jdbc_prefix = "jdbc:hsqldb:file:";
    private static UserPersistenceHSQLDB up;
    private static DiscussionPersistenceHSQLDB dp;

    public CommentPersistenceHSQLDB(String db_path){
        this.db_path = db_path;

        up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true,false);
        dp = (DiscussionPersistenceHSQLDB) PersistenceManager.get_disc_persistence(true, false);
    }

    // Use in memory db
    public CommentPersistenceHSQLDB(String db_path, boolean in_memory){
        this.db_path = db_path;
        this.jdbc_prefix = in_memory ? "jdbc:hsqldb:mem" : jdbc_prefix;

        if(in_memory){
            try{

                Connection conn = connection();
                Statement st = conn.createStatement();

                // Create and populate table
                st.execute("CREATE MEMORY TABLE PUBLIC.COMMENT(ID INTEGER NOT NULL PRIMARY KEY,CONTENT VARCHAR(4000),DATE_CREATED VARCHAR(255),USER INTEGER NOT NULL,DISCUSSION INTEGER NOT NULL);");
                st.execute("INSERT INTO COMMENT VALUES(1,'It was the first day of the rest of her life. This wasnt the day she was actually born, but she knew that nothing would be the same from this day forward.','2022-03-22 00:23:12.000000',1,1);" +
                        "INSERT INTO COMMENT VALUES(2,'It had been a late night. To be more correct, it had been an early morning. It was now 3:00 AM and George was just getting home. He wasnt sure if it had been worth it.','2022-03-12 00:13:12.000000',2,2);" +
                        "INSERT INTO COMMENT VALUES(3,'He was supposed to have been finished by 10:00 PM, but his boss had implored him to stay and help when it was clear they werent going to meet the 10:00 PM target time.','2022-03-02 00:13:12.000000',3,3);" +
                        "INSERT INTO COMMENT VALUES(4,'This was a great discussion!','2022-04-02 00:13:12.000000',4,4);" +
                        "INSERT INTO COMMENT VALUES(5,'I dont think I quite agree with this.','2022-03-02 00:13:12.000000',5,5)");

                conn.commit();

                up = (UserPersistenceHSQLDB) PersistenceManager.get_user_persistence(true,true);
                dp = (DiscussionPersistenceHSQLDB) PersistenceManager.get_disc_persistence(true, true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                System.out.println("Failed to write in memory db");
            }
        }
    }

    private Connection connection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conn = DriverManager.getConnection(jdbc_prefix + db_path + ";shutdown=true", "SA", "");
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }

    @Override
    public void insert_comment(Comment comment) {

        String query = "INSERT INTO comment VALUES(?,?,?,?,?)";

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, get_count()+1);
            statement.setString(2, comment.getContent());
            statement.setString(3, comment.getDate());
            statement.setInt(4, comment.getUser().getId());
            statement.setInt(5, comment.getDiscussion().getId());

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Comment get(String comment) {
        return null;
    }

    @Override
    public void delete_comment(Comment comment) {

        String query = "DELETE FROM comment WHERE id = ?";

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query) ){

            statement.setInt(1, comment.getId() );
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Comment get(String content) {
        String query = "SELECT * FROM comment WHERE content = ?";
        Comment comm = null;

        try(final Connection c = connection();

            PreparedStatement statement = c.prepareStatement(query)){

            statement.setString(1, content);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                User u = up.get(rs.getInt("user"));
                Discussion d = dp.get(rs.getInt("discussion"));
                comm = new Comment(rs.getInt("id"), d, content, null, rs.getString("date_created"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comm;
    }

    @Override
    public ArrayList<Comment> get_all() {

        ArrayList<Comment> queryset = new ArrayList<>();
        String query = "SELECT * FROM comment";

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                // Get user and discussion
                User u = up.get(rs.getInt("user"));
                Discussion d = dp.get(rs.getInt("discussion"));
                String date = String.valueOf(rs.getDate("date_created"));

                queryset.add( new Comment(rs.getInt("id"), d, rs.getString("content"), u, date) );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return queryset;
    }

    @Override
    public int get_count() {
        String query = "SELECT COUNT(*) FROM comment";
        int count = 0;

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            if(rs.next()){
                count = rs.getRow();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
