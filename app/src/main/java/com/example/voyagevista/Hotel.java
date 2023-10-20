package com.example.voyagevista;

public class Hotel {
    String Name, photoMainUrl, url;
    double reviewScore, price;

    public Hotel(String name, String photoMainUrl, double reviewScore, double price, String url) {
        Name = name;
        this.photoMainUrl = photoMainUrl;
        this.reviewScore = reviewScore;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return Name;
    }

    public String getPhotoMainUrl() {
        return photoMainUrl;
    }

    public double getReviewScore() {
        return reviewScore;
    }

    public double getPrice() {
        return price;
    }
    public String getUrl() {
        return url;
    }
}
