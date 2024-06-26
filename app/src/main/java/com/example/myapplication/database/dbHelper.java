package com.example.myapplication.database;


import java.util.ArrayList;

import com.example.myapplication.comparators.ItemComparator;
import com.example.myapplication.models.Item;
import com.example.myapplication.models.MenuItem;

/**
 * This class is the com.example.myapplication.database helper class. It is used to store, add and update
 * items in the com.example.myapplication.database.
 */
public class dbHelper {

    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<MenuItem> menuItems = new ArrayList<>();

    public dbHelper(){
        setItems();
        setMenuItems();
    }

    /**
     * This function will return an ArrayList of Items retrieved from the database.
     * @return an ArrayList of Items that have been retrieved and put into an object.
     */
    public ArrayList<Item> getItems(){
        this.itemSort();
        return this.items;
    }


    /**
     * This function will return an ArrayList of MenuItems retrieved from the database.
     * @return an ArrayList of MenuItems that have been retrieved and put into an object.
     */
    public ArrayList<MenuItem> getMenuItems(){
        return this.menuItems;
    }

    public void setItems(){

        //Todo: Get items fro database - Convert to item objects - Add to items arraylist.
        System.out.println("Getting items from com.example.myapplication.database.");
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
        items.add(new Item("Chicken and Onion Pie sliced awdddddd wad awadw awd awawaw awd adwawd ",1, Item.Type.OTHER,true));
    }

    public void setMenuItems() {
        //Todo: Get items fro database
        menuItems.add(new MenuItem("", MenuItem.Day.MONDAY)) ;
        menuItems.add(new MenuItem("", MenuItem.Day.WEDNESDAY)) ;
        menuItems.add(new MenuItem("", MenuItem.Day.THURSDAY)) ;
        menuItems.add(new MenuItem("", MenuItem.Day.SUNDAY)) ;

        menuItems.add(new MenuItem("Mac and Cheese Balls", MenuItem.Day.SATURDAY)) ;
        menuItems.add(new MenuItem("Chicken wings with egg fried rice and salad", MenuItem.Day.FRIDAY)) ;
        menuItems.add(new MenuItem("Chicken and Rice", MenuItem.Day.TUESDAY)) ;

        //Simulate getting information from the com.example.myapplication.database and altering it.
        //O(n*2) is fine solution considering max it can be is O(49) which is incredible small.
        String name = "Chicken and Wedges";
        MenuItem.Day day = MenuItem.Day.WEDNESDAY;
        for (MenuItem item : menuItems){
            if (item.getDay() == day){
                item.setName(name);
            }
        }
    }

    public void editMenuItem(String day, String name){
        for (MenuItem item : menuItems) {
            if (day.equals(MenuItem.dayStringHashMap.get(item.getDay()))){
                System.out.println("Updated day: " + day);
                item.setName(name);
            }
        }
        //todo: Update DB with correct name.
    }


    /**
     * This function will add an Item object to the database.
     */
    public void addItem(String name){
        Item item = new Item(name,0, Item.Type.OTHER,Boolean.TRUE);
        this.items.add(item);
        //todo: update db
    }


    /**
     * This function will sort the items arrayList using the custom item comparator.
     */
    public void itemSort(){
       this.items.sort(new ItemComparator());
    }
}
