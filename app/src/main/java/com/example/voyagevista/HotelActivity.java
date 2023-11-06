package com.example.voyagevista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelActivity extends AppCompatActivity {
    LinearLayout hoteltextlayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels);
        hoteltextlayout = findViewById(R.id.hoteltextlayout);
        hoteltextlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        setupdestid();
    }

    public void setupdestid(){
        Methods7 methods7 = RetrofitClient5.getRetrofitInstance().create(Methods7.class);
        Call< List<Model7> > call = methods7.getAllData(userinfo.usercityname);
        call.enqueue(new Callback< List<Model7> >() {
            @Override
            public void onResponse(Call < List<Model7> > call, Response< List<Model7> > response) {
                List<Model7> res = response.body();
                userinfo.dest_id = res.get(0).getDest_id();
                setuphotelslist();
            }
            @Override
            public void onFailure(Call < List<Model7> > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void setuphotelslist() {
        ListView listView = findViewById(R.id.superListView3);
        Methods5 methods = RetrofitClient5.getRetrofitInstance().create(Methods5.class);
        Calendar cal = Calendar.getInstance();
        String check_in = Integer.toString(cal.get(Calendar.YEAR)) +  "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-" + Integer.toString(cal.get(Calendar.DATE));
        String check_out = Integer.toString(cal.get(Calendar.YEAR)) +  "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-" + Integer.toString(cal.get(Calendar.DATE)+1);
        Call< Model5 > call2 = methods.getAllData(check_in, userinfo.dest_id, check_out);
        call2.enqueue(new Callback< Model5 >() {
            @Override
            public void onResponse(Call < Model5 > call2, Response< Model5 > response) {
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
                TextView hoteltext = findViewById(R.id.hoteltext);
                hoteltext.setText(hoteltext.getText().toString()+ " in " + userinfo.usercityname);
                hoteltext.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call < Model5 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }
}
