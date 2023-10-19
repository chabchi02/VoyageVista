package com.example.voyagevista;

import java.util.ArrayList;

public class Model5 {
    public ArrayList <results> results;
    public ArrayList <results> getResults() {return results;}
    public class results{
        public String name;
        public String photoMainUrl;
        public double reviewScore;
        public priceBreakdown priceBreakdown;

        public String getName() {
            return name;
        }

        public String getPhotoMainUrl() {
            return photoMainUrl;
        }

        public double getReviewScore() {
            return reviewScore;
        }

        public priceBreakdown getPriceBreakdown() {
            return priceBreakdown;
        }
        public class priceBreakdown{
            public grossPrice grossPrice;

            public grossPrice getGrossPrice() {
                return grossPrice;
            }
            public class grossPrice{
                public double value;

                public double getValue() {
                    return value;
                }
            }
        }
    }
}
