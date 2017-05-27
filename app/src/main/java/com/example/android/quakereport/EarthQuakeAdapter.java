package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jordan on 5/27/2017.
 */

public class EarthQuakeAdapter extends ArrayAdapter {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public EarthQuakeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        EarthQuake quake = (EarthQuake) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView magnitude = (TextView) convertView.findViewById(R.id.quake_mag);
        TextView location = (TextView) convertView.findViewById(R.id.quake_loc);
        TextView time = (TextView) convertView.findViewById(R.id.quake_time);
        magnitude.setText(Double.toString(quake.getMagnitude()));
        location.setText(quake.getLocation());
        time.setText(quake.getDate());
        return convertView;
    }
}
