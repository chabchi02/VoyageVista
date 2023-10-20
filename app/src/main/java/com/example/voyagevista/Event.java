package com.example.voyagevista;

public class Event {
    String Name, Date, Time, Image, Url;
    public Event(String name, String date, String time, String image, String url){
        Name = name;
        Date = date;
        Time = time;
        Image = image;
        Url = url;
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
    public String getUrl() {return Url;}
}
