package com.example.simple_forum.controller.managers;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.persistence.interfaces.IUserPersistence;
import com.example.simple_forum.models.User;
import java.util.ArrayList;

public class UserManager implements BaseManager{

    private static ArrayList<User> userList= new ArrayList<User>();
    private static IUserPersistence up;

    // Current logged in user
    private static User logged_in_user = null;

    // Use stub
    private boolean use_stub = false;

    public UserManager(){ this.use_stub = true; }

    public UserManager(boolean use_local){

        // Get HTTP/SQL
        up = PersistenceManager.get_user_persistence(use_local, false);

        // Update list
        userList = up.get_all();

        // TODO
        // Replace later
        // For now use the first user in the list
        logged_in_user = userList.isEmpty() ? null : userList.get(0);
    }

    // Add a new user to the list
    public void add(Object item){

        // Cast item
        User u = (User) item;

        // Make sure user does not exist already
        if( !exists(u.getUsername()) ){

            if(use_stub) {
                // Add the user object to the list
                userList.add(u);
            } else {

                // Use persistence
                up.insert_user(u);

                // Update list
                userList = up.get_all();
            }
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

    @Override
    // Get object by ID
    public Object get_id(int id) {

        for(User u : userList){
            if(u.getId() == id){
                return u;
            }
        }

        return null;
    }

    // Get object by username
    public Object get_username(String username) {

        for(User u : userList){
            if(u.getUsername().equals(username)){
                return u;
            }
        }

        return null;
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

    // Get/Set logged in user
    public static User get_logged_in_user() {
        // Return stub if the logged in user is not set
        return logged_in_user != null ? logged_in_user : new User();
    }

    public static void set_logged_in_user(User logged_in_user) {
        UserManager.logged_in_user = logged_in_user;
    }

    // Authenticate user
    public boolean auth_user(User u){

        if(up.auth_user(u)) {

            // Set logged in user
            UserManager.logged_in_user = u;

            return true;
        }

        return false;
    }

}
