package com.example.voyagevista;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private Context mContext;
    private int mResource;
    public HotelAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Hotel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView hotelprice = convertView.findViewById(R.id.hotelprice);
        TextView hotelname = convertView.findViewById(R.id.hotelname);
        TextView hotelrating = convertView.findViewById(R.id.hotelrating);
        ImageView image = convertView.findViewById(R.id.image3);

        hotelrating.setText("$" + Double.toString(Math.floor(getItem(position).getPrice() * 100) / 100));
        hotelname.setText(getItem(position).getName());
        hotelprice.setText(Double.toString(getItem(position).getReviewScore())+"/10");
        String url = getItem(position).getPhotoMainUrl();
        Picasso.get().load(url).into(image);
        return convertView;
    }
}
