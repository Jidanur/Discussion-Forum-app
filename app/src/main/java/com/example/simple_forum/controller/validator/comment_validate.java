package com.example.simple_forum.controller.validator;

import com.example.simple_forum.models.Comment;
import com.example.simple_forum.models.Discussion;

public class comment_validate implements Validation{

    private Comment comment_obj;


    public comment_validate(Comment c){
        comment_obj = c;
    }

    public boolean validate(){
        boolean valid =  checkContent()  && checkUser() && checkDiscussion();

        return valid;
    }


    public boolean checkDiscussion(){
        boolean valid = false;

        Discussion d = comment_obj.getDiscussion();

        if(d != null){
            Validation val = new Discussion_validate(d);
            if(val.validate()){
                valid = true;
            }
        }
        return valid;
    }


    public boolean checkContent(){
        boolean valid = false;
        String contents = comment_obj.getContent();
        if(contents != null){
            if(contents.length() <= 4000){
                valid = true;
            }
        }

        return valid;
    }




    @Override
    public boolean checkUser() {
        boolean valid = comment_obj.getUser() != null;

        /// todo
        ///run thro user validator

        return valid;
    }


}
