package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface Methods4 {
    @GET("discovery/v2/events/?apikey=tFNjLQ4dswt07758jehaaeyFIMaUH458&radius=50&locale=*&sort=date,asc")
    Call<Model4> getAllData(@Query("geoPoint") String loc, @Query("startDateTime") String date);
}
