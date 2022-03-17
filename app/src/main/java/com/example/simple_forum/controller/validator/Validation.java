package com.example.simple_forum.controller.validator;

public interface Validation {

    /// check all the fields in this method
    public boolean validate();


    //validates user info
    public boolean checkUser();

    // validate the date
    public boolean checkDate();


}
