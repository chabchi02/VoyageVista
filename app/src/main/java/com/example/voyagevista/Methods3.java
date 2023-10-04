package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods3 {
    @GET("maps/api/place/findplacefromtext/json?key=AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM&inputtype=textquery&fields=photos")
    Call<Model3> getAllData(@Query("input") String name);
}