package com.example.voyagevista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods7 {
    @GET("v1/hotels/locations?locale=en-gb&&rapidapi-key=fc5aa6ec21msh92ac44496d47085p157d4ejsn87d79ef66279")
    Call<List<Model7>> getAllData(@Query("name") String name);
}
