package com.example.simple_forum.controller.managers;

import android.content.Context;

import java.util.ArrayList;

// Base manager interface
public interface BaseManager {

    // Add item
    boolean add(Object item);

    // Get by position
    Object get(int pos);

    // Get by object
    Object get(Object item);

    // Get by id
    Object get_id(int id);

    // Get size
    int size();

    // Get as list
    ArrayList get_list();

    // Check if string exists
    Boolean exists(String text);

    // Clear internal array list
    void clear();
}
