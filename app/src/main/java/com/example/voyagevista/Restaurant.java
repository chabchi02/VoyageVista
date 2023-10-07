package com.example.voyagevista;

public class Restaurant {
    String Name, Address;
    String Image;
    int Price_level;

    public Restaurant(String name, String address, int price_level, String image){
        //Image = image;
        Name = name;
        Address = address;
        Price_level = price_level;
        Image = image;
    }

    public String getImage() {
        return Image;
    }


    public String getresName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public int getPrice_level() {
        return Price_level;
    }

}
