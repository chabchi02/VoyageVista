package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods {
    @GET("json?&radius=1500&type=restaurant&key=AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM")
    Call<Model> getAllData(@Query("location") String loc);
}