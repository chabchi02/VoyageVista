package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods2 {
    @GET("data/reverse-geocode-client?localityLanguage=en")
    Call<Model2> getAllData(@Query("latitude") String lat, @Query("longitude") String lon);
}