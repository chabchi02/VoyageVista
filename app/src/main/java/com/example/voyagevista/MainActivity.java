package com.example.voyagevista;

import static com.android.volley.VolleyLog.TAG;
import static com.google.android.gms.common.util.CollectionUtils.listOf;
import static com.squareup.picasso.Picasso.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private LocationManager locationManager;
    private ListView listView;
    private TextView text3;
    private ImageView cityimage;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocationFromUser();
        //setupsearchbar();
        bnv = findViewById(R.id.bottom_navigation);
        bnv.bringToFront();
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.item_3){
                    Intent intent = new Intent(getApplicationContext(),RestautantActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        }

    private void setupcityname() {
        text3 = findViewById(R.id.text3);
        Methods2 methods2 = RetrofitClient2.getRetrofitInstance().create(Methods2.class);
        String ltlg = userinfo.Lat + "," + userinfo.Long;
        Call< Model2 > call = methods2.getAllData(ltlg,"true","AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM");
        call.enqueue(new Callback < Model2 > () {
            @Override
            public void onResponse(Call < Model2 > call, Response < Model2 > response) {
                Model2.Plus_code results = response.body().getplus_code();
                String[] cityname = results.getcompound_code().split(" ", 2)[1].split(",");
                text3.setText("You're in " + cityname[cityname.length-3].trim() + "!");
                userinfo.usercityname = cityname[cityname.length-3].trim();
                Toast.makeText(getApplicationContext(), cityname[cityname.length-3].trim(), Toast.LENGTH_LONG).show();
                setupcityimage();
            }
            @Override
            public void onFailure(Call < Model2 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupcityimage() {
        cityimage = findViewById(R.id.imageView2);
        Methods3 methods3 = RetrofitClient3.getRetrofitInstance().create(Methods3.class);
        Call< Model3 > call = methods3.getAllData(userinfo.usercityname);
        call.enqueue(new Callback < Model3 > () {
            @Override
            public void onResponse(Call < Model3 > call, Response < Model3 > response) {
                ArrayList< Model3.candidates > results = response.body().getCandidates();
                String citynameimg = results.get(0).getPhotos().get(0).getPhoto_reference();
                String url = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + citynameimg + "&key=AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM&maxwidth=6000&maxheight=6000";
                Picasso.get().load(url).into(cityimage);
            }
            @Override
            public void onFailure(Call < Model3 > call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }


    /*
    private void setupsearchbar() {
        Places.initialize(getApplicationContext(), "AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM");
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment.setTypesFilter(listOf(PlaceTypes.CITIES));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

     */

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
}