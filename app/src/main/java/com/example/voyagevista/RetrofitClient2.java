package com.example.voyagevista;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient2 {
    private static Retrofit retrofit;
    static String lat = MainActivity.Lat;
    static String lon = MainActivity.Long;
    private static String BASE_URL = "https://maps.googleapis.com/";
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}