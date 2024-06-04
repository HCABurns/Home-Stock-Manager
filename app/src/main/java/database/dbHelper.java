package database;

import java.util.ArrayList;

import models.Item;

public class dbHelper {

    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems(){
        System.out.println("YOOO");
        Item item = new Item("Cheese",2, Item.Type.OTHER);
        Item item2 = new Item("Apple",0, Item.Type.FRUIT);
        Item item3 = new Item("Chicken Wings",1, Item.Type.MEAT);
        System.out.println(items);
        items.add(item);
        items.add(item2);
        items.add(item3);
        return items;
    }

    public static void main(String[] args) {



    }

}
