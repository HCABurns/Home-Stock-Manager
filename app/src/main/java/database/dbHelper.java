package database;

import java.util.ArrayList;

import models.Item;

/**
 * This class is the database helper class. It is used to store, add and update
 * items in the database.
 */
public class dbHelper {

    private ArrayList<Item> items = new ArrayList<>();

    /**
     * This function will return an ArrayList of Items retrieved from the database.
     * @return an ArrayList of Items that have been retrieved and put into an object.
     */
    public ArrayList<Item> getItems(){
        System.out.println("Getting items from database.");
        Item item = new Item("Cheese",2, Item.Type.OTHER,false);
        Item item2 = new Item("Apple",0, Item.Type.FRUIT,true);
        Item item3 = new Item("Chicken Wings",1, Item.Type.MEAT,false);
        System.out.println(items);
        items.add(item);
        items.add(item2);
        items.add(item3);
        items.add(new Item("Flour",0, Item.Type.OTHER,true));
        items.add(new Item("Egg",3, Item.Type.OTHER,false));
        items.add(new Item("Butter",1, Item.Type.OTHER,true));
        return items;
    }

    public static void main(String[] args) {



    }

}
