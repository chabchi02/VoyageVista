package com.example.voyagevista;
import java.util.ArrayList;
public class Model3 {
    public ArrayList < candidates > candidates;
    public ArrayList < Model3.candidates > getCandidates() {
        return candidates;
    }
    public class candidates {
        public ArrayList < photos > photos;
        public ArrayList < photos > getPhotos() {
            return photos;
        }

        public class photos{
            public String photo_reference;
            public String getPhoto_reference() {
                return photo_reference;
            }
        }
    }
}