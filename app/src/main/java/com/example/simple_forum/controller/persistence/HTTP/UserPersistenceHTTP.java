package com.example.simple_forum.controller.persistence.HTTP;

import com.example.simple_forum.controller.persistence.interfaces.BaseHTTPInterface;
import com.example.simple_forum.controller.persistence.interfaces.IUserPersistence;
import com.example.simple_forum.models.User;

import java.util.ArrayList;

public class UserPersistenceHTTP implements IUserPersistence, BaseHTTPInterface {

    @Override
    public boolean check_server_status() {
        return false;
    }

    @Override
    public void insert_user(User u) {

    }

    @Override
    public void delete_user(User u) {

    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public ArrayList<User> get_all() {
        return null;
    }

    @Override
    public int get_count() {
        return 0;
    }

    @Override
    public boolean auth_user(User u) {
        return false;
    }
}
