package com.example.voyagevista;

public class Event {
    String Name, Date, Time, Image;
    public Event(String name, String date, String time, String image){
        Name = name;
        Date = date;
        Time = time;
        Image = image;
    }
    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getImage() {return Image;}
}
