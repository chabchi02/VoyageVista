package com.example.voyagevista;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Methods6 {
    @Headers({"Content-Type: application/json", "Authorization: Bearer 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"})
    @GET("v1/chat/completions")
    Call<Model6> getAllData(@Query("model") String model, @Query("messages") JSONArray messages, @Query("temperature") double temperature);
}
