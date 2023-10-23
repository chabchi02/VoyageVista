package com.example.voyagevista;

import java.util.ArrayList;

public class Model6 {
    public ArrayList<choices> choices;
    public ArrayList<choices> getChoices() {return choices;}
    public class choices{
        public message message;
        public message getMessage() {return message;}
        public class message{
            public String content;
            public String getContent() {return content;}
        }
    }
}
