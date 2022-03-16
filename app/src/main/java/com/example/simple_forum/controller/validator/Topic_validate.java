package com.example.simple_forum.controller.validator;

import com.example.simple_forum.models.Topic;

public class Topic_validate implements Validation{

    private Topic topic_obj;

    public Topic_validate(Topic obj){
        topic_obj = obj;
    }

    @Override
    public boolean validate() {
        boolean valid = checkTitle() && checkDate() && checkUser();

        return valid;
    }

    @Override
    public boolean checkDate() {
        boolean valid = topic_obj.getDate_created() != null;

        return valid;
    }

    @Override
    public boolean checkTitle() {
        boolean valid = false;
        String title = topic_obj.getTitle();

        if(title != null) {
            if (title.length() <= 255) {
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public boolean checkUser() {
        boolean valid = topic_obj.getUser() != null;

        /// todo
        ///run thro user validator

        return valid;
    }

}
