package com.example.voyagevista;
import java.util.ArrayList;
public class Model {
    public ArrayList < results > results;
    public ArrayList < Model.results > getResults() {
        return results;
    }
    public void setData(ArrayList < Model.results > data) {
        this.results = results;
    }
    public class results {
        public String name;
        public String getname() {
            return name;
        }
        public void setname(String name) {
            this.name = name;
        }
        }
    }