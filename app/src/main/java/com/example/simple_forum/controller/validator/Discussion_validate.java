package com.example.simple_forum.controller.validator;

import com.example.simple_forum.models.Discussion;
import com.example.simple_forum.models.Topic;

public class Discussion_validate implements Validation{

    private Discussion discussion_obj;


    public Discussion_validate(Discussion obj_to_check){
        discussion_obj = obj_to_check;
    }

    @Override
    public boolean validate() {
        boolean valid = checkTitle() && checkContent() && checkUser() && checkTopic();

        return valid;
    }

    public boolean checkTopic(){

        boolean valid = false;

        Topic t = discussion_obj.getTopic();

        if(t != null){
            Validation val = new Topic_validate(t);
            if(val.validate()){
                valid = true;
            }
        }

        return valid;

    }

    public boolean checkContent(){
        boolean valid = false;
        String contents = discussion_obj.getContent();
        if(contents != null){
            if(contents.length() <= 4000){
                valid = true;
            }
        }

        return valid;
    }




    public boolean checkTitle() {
        boolean valid = false;
        String title = discussion_obj.getTitle();

        if(title != null) {
            if (title.length() <= 255) {
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public boolean checkUser() {
        boolean valid = discussion_obj.getUser() != null;

        /// todo
        ///run thro user validator

        return valid;
    }


}
