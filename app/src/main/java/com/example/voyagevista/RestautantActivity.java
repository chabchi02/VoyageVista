package com.example.voyagevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class RestautantActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setSelectedItemId(R.id.item_3);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        setuprestaurantlist();
    }

    private void setuprestaurantlist() {
        ListView listView = findViewById(R.id.superListView2);
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        String loc = userinfo.Lat + "," + userinfo.Long;
        Call< Model > call = methods.getAllData(loc);
        call.enqueue(new Callback < Model > () {
            @Override
            public void onResponse(Call < Model > call, Response < Model > response) {
                ArrayList< Model.results > results = response.body().getResults();
                String[] names = new String[results.size()];
                for (int i = 0; i < results.size(); i++) {
                    names[i] = "name : " + results.get(i).getname();
                }
                listView.setAdapter(new ArrayAdapter< String >(getApplicationContext(), android.R.layout.simple_list_item_1, names));
            }
            @Override
            public void onFailure(Call < Model > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}
