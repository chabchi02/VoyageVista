package com.example.voyagevista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Calendar;

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
                if (id == R.id.item_4) {
                    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
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
        Calendar cal = Calendar.getInstance();
        String check_in = Integer.toString(cal.get(Calendar.YEAR)) +  "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-" + Integer.toString(cal.get(Calendar.DATE));
        String check_out = Integer.toString(cal.get(Calendar.YEAR)) +  "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-" + Integer.toString(cal.get(Calendar.DATE)+1);
        Call< Model5 > call = methods.getAllData(check_in, "-570760", check_out);
        call.enqueue(new Callback< Model5 >() {
            @Override
            public void onResponse(Call < Model5 > call, Response< Model5 > response) {
                ArrayList< Model5.result > results = response.body().getResult();
                ArrayList<Hotel> resList = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    resList.add(new Hotel(results.get(i).gethotel_name(), results.get(i).getmain_photo_url(), results.get(i).getreview_score(), results.get(i).getmin_total_price(), results.get(i).getUrl()));
                }
                HotelAdapter hotelAdapter = new HotelAdapter(getApplicationContext(), R.layout.list_row3, resList);
                listView.setAdapter(hotelAdapter);
                listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object obj = listView.getAdapter().getItem(position);
                        Uri uri = Uri.parse(results.get(position).getUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onFailure(Call < Model5 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}
