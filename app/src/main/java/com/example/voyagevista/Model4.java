package com.example.voyagevista;

import java.util.ArrayList;

public class Model4 {
    public _embedded _embedded;
    public _embedded get_embedded() {
        return _embedded;
    }
    public class _embedded{
        public ArrayList <events> events;
        public ArrayList < events > getEvents() {
            return events;
        }
        public class events{
            public String name;
            public String url;
            public String getUrl() {return url;}
            public String getName() {
                return name;
            }
            public dates dates;
            public dates getDates() {return dates;}
            public ArrayList<images> images;
            public ArrayList<images> getImages() {return images;}
            public class images{
                public String url;
                public String getUrl() {return url;}
            }
            public class dates{
                public start start;
                public start getstart() {return start;}
                public class start{
                    public String localDate;
                    public String localTime;
                    public String getLocalDate() {return localDate;}
                    public String getLocalTime() {return localTime;}
                }
            }
        }
    }
}
