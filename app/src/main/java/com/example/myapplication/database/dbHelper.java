package com.example.myapplication.database;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.example.myapplication.Activities.MainActivity;
import com.example.myapplication.comparators.ItemComparator;
import com.example.myapplication.models.Item;
import com.example.myapplication.models.MenuItem;
import com.google.firebase.FirebaseApp;
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
    private DatabaseReference id_database;
    private int id_counter = 0;
    //public static Context context;


    public dbHelper(){
        firebaseDatabase = FirebaseDatabase.getInstance(DBInfo.link);
        items_database = firebaseDatabase.getReference("items");
        id_database = firebaseDatabase.getReference("id_counter").child("id");
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


        System.out.println(id_counter);
        System.out.println("-----------------------------------------------------");

        //todo: testing
        //Item item = new Item("Cheese",2, Item.Type.OTHER,false);
        //Item item2 = new Item("Apple",0, Item.Type.FRUIT,true);
        //Item item3 = new Item("Chicken Wings",1, Item.Type.MEAT,false);

        //remove previous 'item' in db
        //mDatabase.child("items").child(item.getName()).removeValue();

        //add item to db
        //mDatabase.child("items").child(item.getName()).setValue(item);
        //mDatabase.child("items").child(item2.getName()).setValue(item2);
        //mDatabase.child("items").child(item3.getName()).setValue(item3);

        //edit item in db
        //mDatabase.child("items").child(item.getName()).child("required").setValue(true);

        //todo: end

        setItems();
        setMenuItems();
    }


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

    public void setItems(){

        //Todo: Get items fro database - Convert to item objects - Add to items arraylist.
        System.out.println("Getting items from com.example.myapplication.database.");
        /*
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
        */

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


    public int getItemPos(String name){

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
            }
        }
        //todo: Update DB with correct name.
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
     */
    public void addItem(String name){
        Item item = new Item(getID() ,name,0, Item.Type.OTHER,Boolean.TRUE);
        this.items.add(item);
        //todo: update db
        items_database.child(item.getId()).setValue(item);
    }


    public void removeItem(String name){
        //todo: update db
        items_database.child(getIDFromName(name)).removeValue();
        items.remove(getItemPos(name));
    }

    public String getIDFromName(String name){
        for (Item item : items){
            System.out.println(item.getName() + " " + name);
            if (Objects.equals(item.getName(), name)){
                System.out.println("REMOVED " + item.getId());
                return item.getId();
            }
        }
        //Unreachable return
        return name;
    }

    /**
     * This function will sort the items arrayList using the custom item comparator.
     */
    public void itemSort(){
       this.items.sort(new ItemComparator());
    }
}
