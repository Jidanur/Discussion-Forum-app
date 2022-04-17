package com.example.simple_forum.controller.persistence.interfaces;

import com.example.simple_forum.models.User;
import java.util.ArrayList;

public interface IUserPersistence {

    // Insert a User into the database
    public void insert_user(User u);

    // Delete a user from the db
    public void delete_user(User u);

    // Get a certain user
    public User get(String username);

    // Get all users in the DB
    public ArrayList<User> get_all();

    // Get the count of all the rows
    public int get_count();
}
