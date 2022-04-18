package com.example.simple_forum.controller.managers;

import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.persistence.interfaces.IDiscussionPersistence;
import com.example.simple_forum.controller.validator.Discussion_validate;
import com.example.simple_forum.controller.validator.Validation;
import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;
import com.example.simple_forum.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiscussionManager implements BaseManager, FilterManager {

    private static ArrayList<Discussion> discussionList = new ArrayList<Discussion>();
    private static IDiscussionPersistence dp;
    private boolean use_stub = false;

    public DiscussionManager() {
        this.use_stub = true;
    }

    public DiscussionManager(boolean use_local) {

        // Get a persistence class, either HSQLDB/HTTP
        dp = PersistenceManager.get_disc_persistence(use_local, false);

        // Update list
        discussionList = dp.get_all();
    }

    // get discussionList of a specific user
    public ArrayList<Discussion> get(User user) {

        ArrayList<Discussion> userDiscussionList = new ArrayList<Discussion>();

        for (int i = 0; i < discussionList.size(); i++) {

            Discussion curr = discussionList.get(i);
            if (curr.getUser() == user) {
                userDiscussionList.add(curr);
            }
        }

        return userDiscussionList;
    }

    // Filter discussion queryset by topic title
    // Blank string implies all
    public ArrayList filter(String title) {

        ArrayList<Discussion> queryset = new ArrayList<Discussion>();

        // If blank string, filter for all
        if (title.equals("")) {
            queryset = discussionList;
        } else {

            // Get any discussion items from discussionList that are a part of topic_title
            // and add them to the queryset
            for (int i = 0; i < discussionList.size(); i++) {
                Discussion d = discussionList.get(i);
                Topic d_topic = d.getTopic();

                if (d_topic != null && d_topic.getTitle().equals(title) && !queryset.contains(d)) {
                    queryset.add(d);
                }
            }
        }

        return queryset;
    }

    @Override
    public void add(Object item) {

        // Cast item
        Discussion d = (Discussion) item;

        // Set user and date
        d.setUser(UserManager.get_logged_in_user());
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d.set_date(dtf.format(new Date()));

        //validation
        Validation discussion_val = new Discussion_validate(d);
        if (discussion_val.validate() && !exists(d.getTitle())) {

            if(use_stub) {
                discussionList.add(d);
            } else {
                dp.insert_disc(d);
                discussionList = dp.get_all();
            }
        }
    }

    @Override
    public Object get(int pos) {
        return discussionList.get(pos);
    }

    // Get by title
    public Object get(String title) {

        for (int i = 0; i < discussionList.size(); i++) {

            Discussion d = discussionList.get(i);
            if (d.getTitle().equals(title)) {
                return d;
            }
        }
        return null;
    }

    @Override
    // Get by object
    public Object get(Object item) {

        int index = discussionList.indexOf((Discussion) item);
        Discussion d = null;

        if (index != -1) {
            d = discussionList.get(index);
        }
        return d;
    }

    @Override
    public Object get_id(int id) {

        for(Discussion d : discussionList){
            if(d.getId() == id){
                return d;
            }
        }

        return null;
    }

    //returns the number of discussions
    public int size() {
        return discussionList.size();
    }

    @Override
    public ArrayList get_list() {
        return discussionList;
    }

    @Override
    public Boolean exists(String text) {

        // Iterate through array list
        for (int i = 0; i < discussionList.size(); i++) {
            if (discussionList.get(i).getTitle().equals(text)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        discussionList.clear();
    }
}
