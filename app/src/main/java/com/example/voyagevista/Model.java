package com.example.voyagevista;
import java.util.ArrayList;
public class Model {
    public ArrayList < results > results;
    public ArrayList < Model.results > getResults() {
        return results;
    }
    public class results {
        public String name, vicinity;
        public int price_level;
        public ArrayList < photos > photos;
        public ArrayList<Model.photos> getPhotos() {
            return photos;
        }
        public String getname() {
            return name;
        }
        public String getVicinity(){ return vicinity; }
        public int getPrice_level(){ return price_level; }
        public void setname(String name) {
            this.name = name;
        }
        }

        public class photos{
            public String photo_reference;

            public String getPhoto_reference(){ return photo_reference; }
        }

    }