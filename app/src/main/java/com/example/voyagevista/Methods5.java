package com.example.voyagevista;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Methods5 {
    @GET("v2/hotels/search?order_by=popularity&adults_number=2&filter_by_currency=CAD&locale=en-gb&units=metric&room_number=1&dest_type=city&include_adjacency=true&page_number=0&categories_filter_ids=class::2,class::4&rapidapi-key=fc5aa6ec21msh92ac44496d47085p157d4ejsn87d79ef66279")
    Call<Model5> getAllData(@Query("checkin_date") String checkin, @Query("dest_id") String dest, @Query("checkout_date") String checkout);
}
