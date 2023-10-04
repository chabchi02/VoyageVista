package com.example.voyagevista;
import java.util.ArrayList;
public class Model2 {
    public Plus_code plus_code;
    public Plus_code getplus_code() {
        return plus_code;
    }
    public void setData(Plus_code data) {
        this.plus_code = plus_code;
    }
    public class Plus_code {
        public String compound_code;
        public String getcompound_code() {
            return compound_code;
        }
        public void setcompound_code(String compound_code) {
            this.compound_code = compound_code;
        }
    }
}