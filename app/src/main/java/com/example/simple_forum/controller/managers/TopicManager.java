package com.example.simple_forum.controller.managers;

import com.example.simple_forum.controller.persistence.interfaces.ITopicPersistence;
import com.example.simple_forum.controller.persistence.PersistenceManager;
import com.example.simple_forum.controller.validator.Topic_validate;
import com.example.simple_forum.controller.validator.Validation;
import com.example.simple_forum.models.Topic;
import java.util.ArrayList;

// Topic manager class for CRUD operations
// implements interface BaseManager
public class TopicManager implements BaseManager {

    private static ArrayList<Topic> topic_list = new ArrayList<Topic>();
    private static ITopicPersistence tp;

    // If stub
    private boolean use_stub = false;

    // Use stub DB
    public TopicManager() {

        // Use stub
        use_stub = true;
    }

    // Use HTTP/API for persistence
    public TopicManager(boolean use_local) {

        // Use HTTP/API based persistence
        tp = PersistenceManager.get_topic_persistence(use_local);

        // Update list
        topic_list = tp.get_all();
    }

    // Add a new topic to the list
    public void add(Object item) {

        // Cast item
        Topic t = (Topic) item;
        System.out.println(t);

        // TODO
        // add(t) should return true or false if it was added via api successfully

        // Make sure title does not exist already
        Validation topic_val = new Topic_validate(t);

        if (topic_val.validate() && !exists(t.getTitle())) {

            if(use_stub){
                topic_list.add(t);
            } else {

                // Add the topic object to the list
                tp.insert_topic(t);

                // Update topic list
                topic_list = tp.get_all();
            }
            t.setId(topic_list.size());
        }
    }

    // Get a topic by title
    public Topic get(String title) {

        for (Topic t : topic_list) {
            if (t.getTitle().equals(title)) {
                return t;
            }
        }

        return null;
    }

    // Get a topic by position
    public Topic get(int pos) {
        return topic_list.get(pos);
    }

    // Get by object
    @Override
    public Object get(Object item) {
        int index = topic_list.indexOf((Topic) item);
        Topic t = null;

        if (index != -1) {
            t = topic_list.get(index);
        }
        return t;
    }

    @Override
    public Object get_id(int id) {

        for(Topic t : topic_list){
            if(t.getId() == id){
                return t;
            }
        }

        return null;
    }

    // Get size
    public int size() {
        return topic_list.size();
    }

    // Return the array list
    public ArrayList get_list() {
        return topic_list;
    }

    @Override
    public Boolean exists(String text) {
        // Iterate through array list
        for (int i = 0; i < topic_list.size(); i++) {
            Topic item = topic_list.get(i);
            if (item != null && item.getTitle().equals(text)) {
                return true;
            }
        }
        return false;
    }

    // Clears array list
    @Override
    public void clear() {
        topic_list.clear();
    }
}
