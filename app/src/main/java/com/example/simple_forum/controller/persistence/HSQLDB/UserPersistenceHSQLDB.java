package com.example.simple_forum.controller.persistence.HSQLDB;
import com.example.simple_forum.controller.persistence.interfaces.IUserPersistence;
import com.example.simple_forum.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserPersistenceHSQLDB implements IUserPersistence {

    private final String db_path;
    private String jdbc_prefix = "jdbc:hsqldb:file:";

    public UserPersistenceHSQLDB(String db_path){ this.db_path = db_path; }

    // Use in memory DB
    // Mainly used for testing
    public UserPersistenceHSQLDB(String db_path, boolean in_memory){
        this.db_path = db_path;
        this.jdbc_prefix = in_memory ? "jdbc:hsqldb:mem:" : jdbc_prefix;

        if(in_memory) {
            try {
                Connection conn = connection();
                Statement stmt = conn.createStatement();

                // Create and populate user table
                stmt.execute("CREATE MEMORY TABLE PUBLIC.USER(ID INTEGER NOT NULL PRIMARY KEY,USERNAME VARCHAR(255) NOT NULL,PASSWORD VARCHAR(255) NOT NULL,EMAIL VARCHAR(255),BIO VARCHAR(2000),UNIQUE(USERNAME))");
                stmt.execute("INSERT INTO USER VALUES(1,'kurt','kurt123','kurt@uofm.com','This is my bio!')" +
                        "INSERT INTO USER VALUES(2,'paul','paul123','paul@uofm.com','This is my bio!')" +
                        "INSERT INTO USER VALUES(3,'jidan','jidan123','jidan@uofm.com','This is my bio!')" +
                        "INSERT INTO USER VALUES(4,'faheem','faheem123','faheem@uofm.com','This is my bio!')" +
                        "INSERT INTO USER VALUES(5,'jiale','jiale123','jiale@uofm.com','This is my bio!')");

                conn.commit();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private Connection connection() throws SQLException {
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

    @Override
    public void insert_user(User u) {
        String query = "INSERT INTO user VALUES(?,?,?,?,?)";

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, get_count()+1);
            statement.setString(2, u.getUsername());
            statement.setString(3, u.getPassword());
            statement.setString(4, u.getEmail());
            statement.setString(5, u.getBio());

            statement.executeUpdate();

            // Commit
            c.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete_user(User u) {

        String query = "DELETE FROM user WHERE username = ?";

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query) ){

            statement.setString(1, u.getUsername() );
            statement.executeUpdate();

            c.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User get(String username) {

        String query = "SELECT id,username,password,email,bio FROM user WHERE username = ?";
        User u = null;

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query)){

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("bio"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    @Override
    public User get(int id) {

        String query = "SELECT id,username,password,email,bio FROM user WHERE id = ?";
        User u = null;

        try(final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query)){

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("bio"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    @Override
    public ArrayList<User> get_all() {
        String query = "SELECT * FROM user";
        ArrayList<User> queryset = new ArrayList<User>();

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                queryset.add( new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("bio")) );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return queryset;
    }

    public int get_count(){
        String query = "SELECT COUNT(*) AS user_count FROM user";
        int count = 0;

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){
            rs.next();
            count = rs.getInt("user_count");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    public boolean auth_user(User u){

        // Try to get the user first
        User user = get(u.getUsername());

        return user != null && u.equals(user);
    }
}
