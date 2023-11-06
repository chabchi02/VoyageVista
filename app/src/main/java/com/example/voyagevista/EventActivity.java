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

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    LinearLayout eventtextlayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        eventtextlayout = findViewById(R.id.eventtextlayout);
        eventtextlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        setEventUpList();
    }

    private void setEventUpList(){
        String ltlg = userinfo.Lat + "," + userinfo.Long;
        ListView listView = findViewById(R.id.superListView3);
        Calendar cal = Calendar.getInstance();
        Methods4 methods4 = RetrofitClient4.getRetrofitInstance().create(Methods4.class);
        String date = Integer.toString(cal.get(Calendar.YEAR)) + "-" + Integer.toString(cal.get(Calendar.MONTH)+1) + "-";
        if (Integer.toString(cal.get(Calendar.DATE)).length()<2){
            date += "0" + Integer.toString(cal.get(Calendar.DATE)) + "T00:00:00Z";
        } else{
            date += Integer.toString(cal.get(Calendar.DATE)) + "T00:00:00Z";
        }
        Call<Model4> call = methods4.getAllData(ltlg, date);
        call.enqueue(new Callback< Model4 >() {
            @Override
            public void onResponse(Call < Model4 > call, Response< Model4 > response) {
                Model4._embedded _embedded = response.body().get_embedded();
                ArrayList<Event> eventList = new ArrayList<>();
                for (int i = 0; i < _embedded.getEvents().size(); i++) {
                    eventList.add(new Event(_embedded.getEvents().get(i).getName(), _embedded.getEvents().get(i).getDates().getstart().getLocalDate(), _embedded.getEvents().get(i).getDates().getstart().getLocalTime(), _embedded.getEvents().get(i).getImages().get(0).getUrl(), _embedded.getEvents().get(i).getUrl()));
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
