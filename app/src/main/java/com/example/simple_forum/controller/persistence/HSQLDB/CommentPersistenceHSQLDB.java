package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.CommentManager;
import com.example.simple_forum.controller.managers.DiscussionManager;
import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.interfaces.ICommentPersistence;
import com.example.simple_forum.models.Comment;
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

public class CommentPersistenceHSQLDB implements ICommentPersistence {

    private final String db_path;

    public CommentPersistenceHSQLDB(String db_path){ this.db_path = db_path; }

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
    public void insert_comment(Comment comment) {

        String query = "INSERT INTO comment VALUES(?,?,?,?,?)";
        CommentManager cm = new CommentManager(true);

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, cm.size()+1);
            statement.setString(2, comment.getContent());
            // TODO
            // Replace placeholder date for now
            statement.setDate(3, Date.valueOf("2022-02-28"));
            statement.setInt(4, comment.getUser().getId());
            statement.setInt(5, comment.getDiscussion().getId());

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public ArrayList<Comment> get_all() {

        ArrayList<Comment> queryset = new ArrayList<Comment>();
        String query = "SELECT * FROM comment";

        // Managers
        UserManager um = new UserManager();
        DiscussionManager dm = new DiscussionManager(true);

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                // Get user and discussion
                User u = (User) um.get_id(rs.getInt("user"));
                Discussion d = (Discussion) dm.get_id(rs.getInt("discussion"));
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
