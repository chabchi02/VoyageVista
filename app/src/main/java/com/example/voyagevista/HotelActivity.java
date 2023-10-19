package com.example.voyagevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelActivity extends AppCompatActivity {
    BottomNavigationView bnv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setSelectedItemId(R.id.item_4);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_2) {
                    Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_3) {
                    Intent intent = new Intent(getApplicationContext(), RestautantActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        setuphotelslist();
    }


    private void setuphotelslist() {
        ListView listView = findViewById(R.id.superListView3);
        Methods5 methods = RetrofitClient5.getRetrofitInstance().create(Methods5.class);
        Call< Model5 > call = methods.getAllData("2023-10-19", "-570760", "2023-10-20");
        call.enqueue(new Callback< Model5 >() {
            @Override
            public void onResponse(Call < Model5 > call, Response< Model5 > response) {
                ArrayList< Model5.results > results = response.body().getResults();
                ArrayList<Hotel> resList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    resList.add(new Hotel(results.get(i).getName(), results.get(i).getPhotoMainUrl(), results.get(i).getReviewScore(), results.get(i).getPriceBreakdown().getGrossPrice().getValue()));
                }
                HotelAdapter hotelAdapter = new HotelAdapter(getApplicationContext(), R.layout.list_row3, resList);
                listView.setAdapter(hotelAdapter);
            }
            @Override
            public void onFailure(Call < Model5 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}
