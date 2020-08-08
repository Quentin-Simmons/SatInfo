package com.example.satinfo;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OldSatelliteAdapter extends ArrayAdapter<Satellite> {

    static class ViewHolder {
        TextView tvSummary;
    }


    private static final String ADAPTER_TAG = "Adapter";
    public OldSatelliteAdapter(Context context, ArrayList<Satellite> satellites) {
        super(context, 0, satellites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final Satellite satellite = getItem(position);
        ViewHolder holder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sat_summary, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Lookup view for data population
        TextView tvSummary = (TextView) convertView.findViewById(R.id.summary);
        tvSummary.setText(String.valueOf(satellite.svid));

        // Return the completed view to render on screen
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ADAPTER_TAG,"Clicked on the view. ");
                Intent intent = new Intent(getContext(), SatDetails.class);
                intent.putExtra(Satellite.SVID_TAG,String.valueOf(satellite.svid));
                intent.putExtra(Satellite.AZIMUTH_TAG,   String.valueOf(satellite.azimuth));
                intent.putExtra(Satellite.CN0DHZS_TAG, String.valueOf(satellite.cn0DHzs));
                intent.putExtra(Satellite.ELEVATION_TAG, String.valueOf(satellite.elevation));
                intent.putExtra(Satellite.CONSTELLATION_TAG,String.valueOf(satellite.constellationType));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        });
        return convertView;
    }
}