package com.example.voyagevista;

import java.util.ArrayList;

public class Model5 {
    public ArrayList <result> result;
    public ArrayList <result> getResult() {return result;}
    public class result{
        public String hotel_name;
        public String main_photo_url;
        public double review_score;
        public double min_total_price;
        public String url;
        public String gethotel_name() {
            return hotel_name;
        }

        public String getmain_photo_url() {
            return main_photo_url;
        }

        public double getreview_score() {
            return review_score;
        }

        public double getmin_total_price() {
            return min_total_price;
        }
        public String getUrl() {return url;}

    }
}
