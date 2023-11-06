package com.example.voyagevista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    LinearLayout restauranttextlayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);
        restauranttextlayout = findViewById(R.id.restauranttextlayout);
        restauranttextlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
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
                ArrayList<Restaurant> resList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    resList.add(new Restaurant(results.get(i).getname(), results.get(i).getVicinity(), results.get(i).getPrice_level(), results.get(i).getPhotos().get(0).getPhoto_reference()));
                }
                RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getApplicationContext(), R.layout.list_row, resList);
                listView.setAdapter(restaurantAdapter);
                TextView restauranttext = findViewById(R.id.restauranttext);
                restauranttext.setText(restauranttext.getText().toString()+ " in " + userinfo.usercityname);
                restauranttext.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call < Model > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}
