package com.example.simple_forum.controller.validator;

import com.example.simple_forum.models.Topic;

public class Topic_validate implements Validation{

    private Topic topic_obj;

    public Topic_validate(Topic obj){
        topic_obj = obj;
    }

    @Override
    public boolean validate() {
        boolean valid = checkTitle();

        return valid;
    }


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


}
