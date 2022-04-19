package com.example.simple_forum.controller.persistence.HSQLDB;

import com.example.simple_forum.controller.managers.TopicManager;
import com.example.simple_forum.controller.managers.UserManager;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
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

public class DiscussionPersistenceHSQLDB implements IDiscussionPersistence {

    private final String db_path;
    private String jdbc_prefix = "jdbc:hsqldb:file:";

    public DiscussionPersistenceHSQLDB(String db_path) { this.db_path = db_path; }

    // Use in memory DB
    public DiscussionPersistenceHSQLDB(String db_path, boolean in_memory){
        this.db_path = db_path;
        this.jdbc_prefix = in_memory ? "jdbc:hsqldb:mem" : jdbc_prefix;

        if(in_memory){
            try{
                Connection conn = connection();
                Statement st = conn.createStatement();

                // Create and populate table
                st.execute("CREATE MEMORY TABLE PUBLIC.DISCUSSION(ID INTEGER NOT NULL PRIMARY KEY,TITLE VARCHAR(255) NOT NULL,CONTENT VARCHAR(4000),DATE_CREATED TIMESTAMP,USER INTEGER NOT NULL,TOPIC VARCHAR(255) NOT NULL,UNIQUE(TITLE))");
                st.execute("INSERT INTO DISCUSSION VALUES(1,'Good Music','the chair sat in the corner where it had been for over 25 years. The only difference was there was someone actually sitting in it. How long had it been since someone had done that? Ten years or more he imagined. Yet there was no denying the presence in the chair now','2022-02-28 23:44:00.000000',1,'Music');" +
                        "INSERT INTO DISCUSSION VALUES(2,'The chic gangster liked to start the day with a pink scarf.','Ten more steps. If he could take ten more steps it would be over, but his legs wouldnt move. He tried to will them to work, but they wouldnt listen to his brain. Ten more steps and it would be over but it didnt appear he would be able to do it.','2022-02-28 21:44:00.000000',2,'Movies');" +
                        "INSERT INTO DISCUSSION VALUES(3,'That is an appealing treasure map that I can use','There were a variety of ways to win the game. James had played it long enough to know most of them and he could see what his opponent was trying to do. There was a simple counterattack that James could use and the game should be his. He began deploying it with the confidence of a veteran player who had been in this situation a thousand times in the past. So, it was with great surprise when his opponent used a move he had never before seen or anticipated to easily defeat him in the game.','2022-02-28 22:33:00.000000',3,'Video Games');" +
                        "INSERT INTO DISCUSSION VALUES(4,'A suit of armor provides excellent sun protection on hot days.','It was supposed to be a dream vacation. They had planned it over a year in advance so that it would be perfect in every way. It had been what they had been looking forward to through all the turmoil and negativity around them. It had been the light at the end of both their tunnels. Now that the dream vacation was only a week away, the virus had stopped all air travel.','2022-02-28 22:54:00.000000',4,'Cars');" +
                        "INSERT INTO DISCUSSION VALUES(5,'Standing on ones head at job interviews forms a lasting impression.','The water rush down the wash and into the slot canyon below. Two hikers had started the day to sunny weather without a cloud in the sky, but they hadnt thought to check the weather north of the canyon. Huge thunderstorms had brought a deluge o rain and produced flash floods heading their way. The two hikers had no idea what was coming.','2022-02-28 22:54:00.000000',5,'Popular');");

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
    public void insert_disc(Discussion d) {
        String query = "INSERT INTO discussion VALUES(?,?,?,?,?,?)";
        User user = d.getUser();

        try{

            final Connection c = connection();
            PreparedStatement statement = c.prepareStatement(query);

            statement.setInt(1, get_count()+1);
            statement.setString(2, d.getTitle() );
            statement.setString(3, d.getContent() );
            statement.setString(4, d.getDate());
            statement.setInt(5, user.getId());
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
        String query = "SELECT id,title,content,user,date_created,topic FROM discussion WHERE title = ?";
        Discussion d = null;
        TopicManager tm = new TopicManager(true);
        UserManager um = new UserManager(true);

        try(final Connection c = connection();

            PreparedStatement statement = c.prepareStatement(query);){

            statement.setString(1, title);
            ResultSet rs = statement.executeQuery(query);

            if(rs.next()){
                User u = (User) um.get_id(rs.getInt("user"));
                Topic t = tm.get(rs.getString("topic"));
                d = new Discussion(t, rs.getString("title"), rs.getString("content"), u, rs.getString("date_created"));
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
        UserManager um = new UserManager(true);

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while(rs.next()){

                // Get the topic and user
                Topic t = tm.get(rs.getString("topic"));
                User user = (User) um.get_id(rs.getInt("user"));

                queryset.add( new Discussion(rs.getInt("id"), t, rs.getString("title"), rs.getString("content"), user, rs.getString("date_created")) );
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return queryset;
    }

    @Override
    public int get_count() {
        String query = "SELECT COUNT(*) AS discussion_count FROM discussion";
        int count = 0;

        try(final Connection c = connection();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)){
            rs.next();
            count = rs.getInt("discussion_count");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
