package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods5 {
    @GET("v1/hotels/search?dest_type=city&units=metric&adults_number=2&order_by=popularity&filter_by_currency=CAD&locale=en-gb&room_number=1&children_number=2&children_ages=5%2C0&categories_filter_ids=class::2,class::4&page_number=0&include_adjacency=true&rapidapi-key=fc5aa6ec21msh92ac44496d47085p157d4ejsn87d79ef66279")
    Call<Model5> getAllData(@Query("checkin_date") String checkin, @Query("dest_id") String dest, @Query("checkout_date") String checkout);
}