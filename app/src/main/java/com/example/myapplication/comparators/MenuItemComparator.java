package com.example.myapplication.comparators;

import java.util.Comparator;

import models.MenuItem;

public class MenuItemComparator implements Comparator<MenuItem> {
    @Override
    public int compare(MenuItem menuItem, MenuItem t1) {

        if (menuItem.getDay().ordinal() > t1.getDay().ordinal()) {
            return 1;
        }
        return -1;
    }
}
