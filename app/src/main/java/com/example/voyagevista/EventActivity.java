package com.example.voyagevista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class EventActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setSelectedItemId(R.id.item_2);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_3) {
                    Intent intent = new Intent(getApplicationContext(), RestautantActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(id == R.id.item_4){
                    Intent intent = new Intent(getApplicationContext(),HotelActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        setEventUpList();
    }

    private void setEventUpList(){
        String ltlg = userinfo.Lat + "," + userinfo.Long;
        ListView listView = findViewById(R.id.superListView3);
        Calendar cal = Calendar.getInstance();
        Methods4 methods4 = RetrofitClient4.getRetrofitInstance().create(Methods4.class);
        String date = Integer.toString(cal.get(Calendar.YEAR)) + "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-" +Integer.toString(cal.get(Calendar.DATE)) + "T00:00:00Z";
        Call<Model4> call = methods4.getAllData(ltlg, date);
        call.enqueue(new Callback< Model4 >() {
            @Override
            public void onResponse(Call < Model4 > call, Response< Model4 > response) {
                Model4._embedded _embedded = response.body().get_embedded();
                ArrayList<Event> eventList = new ArrayList<>();
                for (int i = 0; i < _embedded.getEvents().size(); i++) {
                    eventList.add(new Event(_embedded.getEvents().get(i).getName(), _embedded.getEvents().get(i).getDates().getstart().localDate, _embedded.getEvents().get(i).getDates().getstart().localTime, _embedded.getEvents().get(i).getImages().get(0).getUrl(), _embedded.getEvents().get(i).getUrl()));
                }
                EventAdapter restaurantAdapter = new EventAdapter(getApplicationContext(), R.layout.list_row2, eventList);
                listView.setAdapter(restaurantAdapter);
                listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object obj = listView.getAdapter().getItem(position);
                        Uri uri = Uri.parse(_embedded.getEvents().get(position).getUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                TextView eventtext = findViewById(R.id.eventtext);
                eventtext.setText(eventtext.getText().toString()+ " in " + userinfo.usercityname);
                eventtext.setVisibility(View.VISIBLE);

            }
            @Override
            public void onFailure(Call < Model4 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

    }
}
