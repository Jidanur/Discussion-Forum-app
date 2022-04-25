package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.User;
import java.util.ArrayList;

public interface IUserPersistence {

    // Insert a User into the database
    void insert_user(User u);

    // Delete a user from the db
    void delete_user(User u);

    // Get a certain user
    User get(String username);
    User get(int id);

    // Get all users in the DB
    ArrayList<User> get_all();

    // Get the count of all the rows
    int get_count();

    // Authenticate user
    boolean auth_user(User u);
}
