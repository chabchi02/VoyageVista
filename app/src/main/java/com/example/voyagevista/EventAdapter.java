package com.example.voyagevista;

import android.content.Context;
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

public class EventAdapter extends ArrayAdapter<Event> {
    private Context mContext;
    private int mResource;
    public EventAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Event> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView eventname = convertView.findViewById(R.id.eventname);
        TextView eventdate = convertView.findViewById(R.id.eventdate);
        TextView eventtime = convertView.findViewById(R.id.eventime);
        ImageView image = convertView.findViewById(R.id.image2);

        eventname.setText(getItem(position).getName());
        eventdate.setText(getItem(position).getDate());
        eventtime.setText(getItem(position).getTime());
        String url = getItem(position).getImage();
        Picasso.get().load(url).into(image);
        return convertView;
    }

}
