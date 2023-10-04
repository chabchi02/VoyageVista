package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods2 {
    @GET("maps/api/geocode/json?")
    Call<Model2> getAllData(@Query("latlng") String ltlg, @Query("sensor") String val, @Query("key") String apikey);
}