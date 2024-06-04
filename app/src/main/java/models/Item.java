package models;

import androidx.annotation.NonNull;

public class Item {

    private String name;
    private int count;

    private Type type;

    public enum Type{
        VEGETABLE,
        FRUIT,
        MEAT,
        OTHER
    }

    public Item(String name, int count, Type type){
        this.name = name;
        this.count = count;
        this.type = type;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getCount() {return count;}

    public void setCount(int count) {this.count = count;}

    public Type getType() {return type;}

    public void setType(Type type) {this.type = type;}

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", type=" + type +
                '}';
    }

    public static void main(String[] args) {
        Item item = new Item("Cucumber",1, Type.VEGETABLE);
        Item item2 = new Item("Chicken Portions",3,Type.MEAT);
        Item item3 = new Item("Plum Tomatoes",8,Type.VEGETABLE);
        Item item4 = new Item("Yogurt",1,Type.OTHER);

        System.out.println(item);
    }

}
