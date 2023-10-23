package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods6 {
    @GET("data/3.0/onecall?appid=04c63da288cb03237ebaa0150716b8f0&exclude=hourly,daily,minutely&units=metric")
    Call<Model6> getAllData(@Query("lat") String lat, @Query("lon") String lon);
}
