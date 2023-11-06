package com.example.voyagevista;

import static com.android.volley.VolleyLog.TAG;
import static com.google.android.gms.common.util.CollectionUtils.listOf;
import static com.squareup.picasso.Picasso.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URI;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private TextView text3, welcomeText;
    private ImageView cityimage;
    FloatingActionButton fab;
    BottomNavigationView bnv;
    CardView roundedButton, roundedButton2, roundedButton3, createitinerary;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(60000, TimeUnit.MILLISECONDS).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_v2);
        getLocationFromUser();
        welcomeText = findViewById(R.id.textView);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.item_2){
                    Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        if (userinfo.name.length()>0){
            welcomeText.setText("Hi, " + userinfo.name);
        }
        roundedButton = findViewById(R.id.roundedButton);
        roundedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HotelActivity.class);
                startActivity(intent);
            }
        });
        roundedButton2 = findViewById(R.id.roundedButton2);
        roundedButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EventActivity.class);
                startActivity(intent);
            }
        });
        roundedButton3 = findViewById(R.id.roundedButton3);
        roundedButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RestautantActivity.class);
                startActivity(intent);
            }
        });
        fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
        createitinerary = findViewById(R.id.createitinerary);
        createitinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daysPopup();
            }
        });

    }
    private void setupcityname() {
        text3 = findViewById(R.id.text3);
        Methods2 methods2 = RetrofitClient2.getRetrofitInstance().create(Methods2.class);
        Call< Model2 > call = methods2.getAllData(userinfo.Lat, userinfo.Long);
        call.enqueue(new Callback < Model2 > () {
            @Override
            public void onResponse(Call < Model2 > call, Response < Model2 > response) {
                userinfo.usercityname = response.body().getCity().trim();
                setupcityimage();
            }
            @Override
            public void onFailure(Call < Model2 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupcityimage() {
        cityimage = findViewById(R.id.cityImage);
        Methods3 methods3 = RetrofitClient3.getRetrofitInstance().create(Methods3.class);
        Call< Model3 > call = methods3.getAllData(userinfo.usercityname);
        call.enqueue(new Callback < Model3 > () {
            @Override
            public void onResponse(Call < Model3 > call, Response < Model3 > response) {
                ArrayList< Model3.candidates > results = response.body().getCandidates();
                String citynameimg = results.get(0).getPhotos().get(0).getPhoto_reference();
                String url = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + citynameimg + "&key=AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM&maxwidth=6000&maxheight=6000";
                Picasso.get().load(url).into(cityimage);
                text3.setText(userinfo.usercityname);
                getWeather();
            }
            @Override
            public void onFailure(Call < Model3 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getWeather(){
        Methods6 methods6 = RetrofitClient6.getRetrofitInstance().create(Methods6.class);
        Call< Model6 > call = methods6.getAllData(userinfo.Lat, userinfo.Long);
        call.enqueue(new Callback < Model6 > () {
            @Override
            public void onResponse(Call < Model6 > call, Response < Model6 > response) {
                text3.setText(text3.getText().toString() + ", " + (int)Math.round(response.body().getCurrent().getTemp()) + "°C");
            }
            @Override
            public void onFailure(Call < Model6 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getLocationFromUser() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION )!= PackageManager.PERMISSION_GRANTED) && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                userinfo.Lat = String.valueOf(location.getLatitude());
                userinfo.Long = String.valueOf(location.getLongitude());
                setupcityname();
            }
        });
    }

    private void daysPopup(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(MainActivity.this.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.dialog,null, false);
        Spinner spinner = formElementsView.findViewById(R.id.spinner);

        final Spinner _spinner = formElementsView.findViewById(R.id.spinner);

        String[] vvalue = new String[]{
                "1 day",
                "2 days",
                "3 days",
                "4 days",
                "5 days"
        };
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, vvalue);
        spinnerArrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(spinnerArrayAdapter);

        new AlertDialog.Builder(MainActivity.this).setView(formElementsView).setTitle("How many days are you in " + userinfo.usercityname + "?").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userinfo.desireddays = spinner.getSelectedItem().toString().substring(0);
                Intent intent = new Intent(getApplicationContext(),ItineraryActivity.class);
                startActivity(intent);
            }
        }).show();
    }

    void changeUI(String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
}