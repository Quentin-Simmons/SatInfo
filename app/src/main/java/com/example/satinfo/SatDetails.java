package com.example.satinfo;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SatDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sat_detail);
        Intent intent = getIntent();
        String svid = intent.getStringExtra(Satellite.SVID_TAG);
        String azimuth =  intent.getStringExtra(Satellite.AZIMUTH_TAG);
        String cn0DHzs =  intent.getStringExtra(Satellite.CN0DHZS_TAG);
        String elevation = intent.getStringExtra(Satellite.ELEVATION_TAG);
        String constellationType = intent.getStringExtra(Satellite.CONSTELLATION_TAG);




        TextView tvSvid = (TextView) findViewById(R.id.svid);
        TextView tvCn0DHzs = (TextView) findViewById(R.id.cn0DHzs);
        TextView tvAzimuth = (TextView) findViewById(R.id.azimuth);
        TextView tvConstellationType = (TextView) findViewById(R.id.constellationType);
        TextView tvElevation = (TextView) findViewById(R.id.elevation);


        tvSvid.setText(svid);
        tvAzimuth.setText(azimuth);
        tvCn0DHzs.setText(cn0DHzs);
        tvElevation.setText(elevation);
        tvConstellationType.setText(constellationType);
    }
}