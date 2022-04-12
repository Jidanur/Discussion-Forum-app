package com.example.simple_forum.controller.managers;
import com.example.simple_forum.models.User;
import java.util.ArrayList;

public class UserManager implements BaseManager{

    private static ArrayList<User> userList= new ArrayList<User>();

    public UserManager(){}

    // Add a new user to the list
    public void add(Object item){

        // Cast item
        User u = (User) item;

        // Make sure user does not exist already
        if( !exists(u.getUsername()) ){
            // Add the topic object to the list
            userList.add(u);
        }
    }

    // Get a user by position
    public Object get(int pos){
        return userList.get(pos);
    }

    // Get by object
    @Override
    public Object get(Object item) {
        int index  = userList.indexOf((User) item);
        User u = null;

        if (index != -1){
            u = userList.get(index);
        }
        return u;
    }

    // Get size
    public int size(){
        return userList.size();
    }

    // Return the array list
    public ArrayList get_list(){
        return userList;
    }

    @Override
    public Boolean exists(String text) {
        // Iterate through array list
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getUsername().equals(text)){
                return true;
            }
        }
        return false;
    }

    // Clears array list
    @Override
    public void clear() {
        userList.clear();
    }

}
