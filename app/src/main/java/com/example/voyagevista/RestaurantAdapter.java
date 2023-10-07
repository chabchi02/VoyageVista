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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private Context mContext;
    private int mResource;
    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView resname = convertView.findViewById(R.id.resname);
        TextView resaddress = convertView.findViewById(R.id.resaddress);
        TextView price_level = convertView.findViewById(R.id.price_level);
        ImageView image = convertView.findViewById(R.id.image);

        resname.setText(getItem(position).getresName());
        resaddress.setText(getItem(position).getAddress());
        int pricelvl = getItem(position).getPrice_level();
        String priceres = "";
        while(pricelvl!=0) {
            priceres += "$";
            pricelvl -= 1;
        }
        price_level.setText(priceres);
        String url = "https://maps.googleapis.com/maps/api/place/photo?photoreference=" + getItem(position).getImage() + "&key=AIzaSyA5jevoRIytpKmKovpxlmASmrheQ6s_9jM&maxwidth=6000&maxheight=6000";
        Picasso.get().load(url).into(image);
        return convertView;
    }
}
