package com.example.myapplication.models;

/**
 * This class is used to hold information regarding an item.
 *
 * Parameters:
 * name : String - This is the name of the item
 * count : Integer - This is the amount of items stored.
 * type : Enum - This is the type of the item.
 * required : Boolean - This is a flag to indicate if the item is required to be bought or not.
 */
public class Item {
    private String name;
    private int count;
    private Type type;
    private Boolean required;
    public enum Type{
        VEGETABLE,
        FRUIT,
        MEAT,
        OTHER
    }

    /**
     * Creates an item object with all the required variables.
     * @param name Name of the item.
     * @param count Amount of the item.
     * @param type Type of the tem.
     * @param required Boolean flag to indicate if the item is required to be bought or not.
     */
    public Item(String name, int count, Type type, Boolean required){
        this.name = name;
        this.count = count;
        this.type = type;
        this.required = required;
    }


    /**
     * Gets the name of the item.
     * @return A string representing the name of the item.
     */
    public String getName() {return name;}


    /**
     * This function will allow for changing the name of an item.
     * @param name A string given to change the name of the item.
     */
    public void setName(String name) {this.name = name;}


    /**
     * Gets the amount stored of the item.
     * @return An integer representing the quantity stored of the item.
     */
    public int getCount() {return count;}


    /**
     * This function will allow for changing the count of an item.
     * @param count A integer given to change the count of the item.
     */
    public void setCount(int count) {this.count = count;}


    /**
     * Gets the type of the item.
     * @return A string representing the type of the item.
     */
    public Type getType() {return type;}


    /**
     * This function will allow for changing the type of an item.
     * @param type A Type enum given to change the type of the item.
     */
    public void setType(Type type) {this.type = type;}


    /**
     * Gets the required flag of the item.
     * @return A boolean representing if the item is required to be bought or not.
     */
    public Boolean getRequired() {
        return required;
    }


    /**
     * This function will allow for changing the required flag of an item.
     * @param required A boolean given to change the required flag of the item.
     */
    public void setRequired(Boolean required) {
        this.required = required;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", type=" + type +
                ", required=" + required +
                '}';
    }

    public static void main(String[] args) {
        Item item = new Item("Cucumber",1, Type.VEGETABLE,false);
        Item item2 = new Item("Chicken Portions",3,Type.MEAT,false);
        Item item3 = new Item("Plum Tomatoes",8,Type.VEGETABLE,false);
        Item item4 = new Item("Yogurt",1,Type.OTHER,false);

        System.out.println(item);
    }

}
