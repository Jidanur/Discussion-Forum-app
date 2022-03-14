package com.example.simple_forum.controller.managers;

import java.util.ArrayList;

public interface FilterManager {

    public void filter(String title);
    public ArrayList get_queryset();
}
