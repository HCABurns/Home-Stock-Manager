package com.example.myapplication.database;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.myapplication.comparators.ItemComparator;
import com.example.myapplication.comparators.MenuItemComparator;
import com.example.myapplication.models.Item;
import com.example.myapplication.models.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * This class is the com.example.myapplication.database helper class. It is used to store, add and update
 * items in the com.example.myapplication.database.
 */
public class dbHelper {

    public ArrayList<Item> items = new ArrayList<>();
    public ArrayList<MenuItem> menuItems = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference items_database;
    private DatabaseReference menu_database;
    private DatabaseReference id_database;
    private int id_counter = 0;


    /**
     * This constructor will connect to the database and fill the items and menu arrays.
     */
    public dbHelper(){
        firebaseDatabase = FirebaseDatabase.getInstance(DBInfo.link);
        items_database = firebaseDatabase.getReference(DBInfo.db_name);
        menu_database = firebaseDatabase.getReference(DBInfo.db_name3);
        id_database = firebaseDatabase.getReference(DBInfo.db_name2).child("id");
        id_database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id_counter = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
        setItems();
        setMenuItems();
    }


    /**
     * Get the id for an item being added to the list.
     * @return Integer - Integer of the id.
     */
    public String getID(){
        id_counter += 1;
        id_database.setValue(id_counter);
        return String.valueOf(id_counter);

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

    /**
     * Fill items arrayList with the items from the database.
     */
    public void setItems(){
        // Get items from the database and add to items arraylist.
        items_database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Item item = ds.getValue(Item.class);
                    items.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    /**
     * Fill menuitems arrayList with the strings related to menu from the database.
     */
    public void setMenuItems() {
        // Add items to the menu arraylist.
        menu_database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuItems.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    MenuItem item = ds.getValue(MenuItem.class);
                    menuItems.add(item);
                }
                // Custom sort the menu - Monday -> Sunday
                menuItems.sort(new MenuItemComparator());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    /**
     * Return the position in the array of a given item name.
     * @param name - String of the item position to be returned.
     * @return Integer - Index of the item in the array or -1 if not in the list.
     */
    public int getItemPos(String name){
        // Get the position of the item.
        for (int pos = 0; pos<items.size();pos++){
            if (items.get(pos).getName().equals(name)){
                return pos;
            }
        }
        return -1;
    }


    public void editMenuItem(String day, String name){
        for (MenuItem item : menuItems) {
            if (day.equals(MenuItem.dayStringHashMap.get(item.getDay()))){
                System.out.println("Updated day: " + day);
                item.setName(name);
                //todo: Update DB with correct name.
                menu_database.child(String.valueOf(item.getDay())).setValue(item);
            }
        }

    }


    public void editItem(String originalName, String name, int quantity){
        int pos = getItemPos(originalName);
        Item item = items.get(pos);
        System.out.println("Item : " + item);
        item.setName(name);
        item.setCount(quantity);

        //todo update the database item
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(item.getId(), item);
        items_database.updateChildren(childUpdates);
        System.out.println("Editted by " + item);
        System.out.println(childUpdates);
        System.out.println();
    }

    public void incrementQuantity(Item item){
        item.setCount(item.getCount()+1);
        items_database.child(item.getId()).child("count").setValue(item.getCount());
    }

    public void decrementQuantity(Item item){
        item.setCount(item.getCount()-1);
        items_database.child(item.getId()).child("count").setValue(item.getCount());
    }


    public void editItemRequired(String name, boolean required){
        int pos = getItemPos(name);
        Item item = items.get(pos);
        item.setRequired(required);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(item.getId(), item);
        items_database.updateChildren(childUpdates);
    }


    /**
     * This function will add an Item object to the database.
     * @param name - Name of item to remove from the database.
     */
    public void addItem(String name){
        Item item = new Item(getID() ,name,0, Item.Type.OTHER,Boolean.TRUE);
        this.items.add(item);
        // Update database.
        items_database.child(item.getId()).setValue(item);
    }

    /**
     * This function will remove an item from the database.
     * @param name - Name of item to remove from the database.
     */
    public void removeItem(String name){
        items_database.child(getIDFromName(name)).removeValue();
        items.remove(getItemPos(name));
    }


    /**
     * This function will get the item id given the name string.
     * @param name - Name of item to remove from the database.
     */
    public String getIDFromName(String name){
        for (Item item : items){
            System.out.println(item.getName() + " " + name);
            if (Objects.equals(item.getName(), name)){
                return item.getId();
            }
        }
        // Unreachable return - Given valid name.
        return name;
    }

    /**
     * This function will sort the items arrayList using the custom item comparator.
     */
    public void itemSort(){
       this.items.sort(new ItemComparator());
    }
}
