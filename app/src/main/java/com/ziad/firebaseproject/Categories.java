package com.ziad.firebaseproject;

public class Categories {
    String type;
    String Name;
    String Number;
    String Price;


    public Categories() {
    }

    public Categories(String type, String name, String number, String price) {
        this.type = type;
        Name = name;
        Number = number;
        Price = price;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }

    public String getPrice() {
        return Price;
    }


}
