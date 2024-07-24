package com.example.myapplication.models;

import java.util.HashMap;

/**
 * This is the menu item class. It holds the information regarding a menu item.
 */
public class MenuItem {

    private String name;
    private Day day;
    public enum Day{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
    public static HashMap<Day,String> dayStringHashMap = new HashMap<Day,String>(){{
        put(Day.MONDAY , "Monday");
        put(Day.TUESDAY , "Tuesday");
        put(Day.WEDNESDAY , "Wednesday");
        put(Day.THURSDAY , "Thursday");
        put(Day.FRIDAY , "Friday");
        put(Day.SATURDAY , "Saturday");
        put(Day.SUNDAY , "Sunday");
    }};

    /**
     * Constructor for the menu item.
     * @param name Name of the dish to be made.
     * @param day An Enum regarding the day of the dish.
     */
    public MenuItem(String name, Day day) {
        this.name = name;
        this.day = day;
    }

    public MenuItem(){}

    /**
     * @return String - Name of the dish.
     */
    public String getName() {
        return name;
    }


    /**
     * @param name String of the name of the dish.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Enum of the day the dish is to be made.
     */
    public Day getDay() {
        return day;
    }


    /**
     * @param day Enum of the day that the dish will be.
     */
    public void setDay(Day day) {
        this.day = day;
    }

    public static void main(String[] args) {
        MenuItem item = new MenuItem("Mac and Cheese" , Day.SATURDAY);
        System.out.println(item.getName());;
    }
}
