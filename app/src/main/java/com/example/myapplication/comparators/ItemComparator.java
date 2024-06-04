package com.example.myapplication.comparators;

import java.util.Comparator;

import models.Item;

/**
 * This class is a custom comparator used for comparing two items.
 * Usage: Sorting an arraylist of items from smallest to largest to allow users to know which
 * items that are out of stock.
 */
public class ItemComparator implements Comparator<Item> {
    /**
     * @param item Item 1 to be compared.
     * @param t1 Item 2 to be compared.
     * @return Integer: Returns 1 if item 1 has a larger count than item 2. 0 if equal counts and -1
     *                  if the second item has a larger count.
     */
    @Override
    public int compare(Item item, Item t1) {
        if (item.getCount() > t1.getCount()){
            return 1;
        }
        else if (item.getCount() == t1.getCount()){
            return 0;
        }
        return -1;
    }
}
