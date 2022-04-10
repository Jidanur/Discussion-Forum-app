package com.example.simple_forum.controller.managers;

import android.content.Context;

import java.util.ArrayList;

// Base manager interface
public interface BaseManager {

    // Add items via json string
    public void add_json_str(String data);

    // Add item
    public void add(Object item);

    // Get by position
    public Object get(int pos);

    // Get by object
    public Object get(Object item);

    // Get size
    public int size();

    // Get as list
    public ArrayList get_list();

    // Check if string exists
    public Boolean exists(String text);

    // Clear internal array list
    public void clear();
}
