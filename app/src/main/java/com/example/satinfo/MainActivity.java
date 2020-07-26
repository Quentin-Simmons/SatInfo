package com.example.satinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String MAIN_TAG = "SAT_INFO";
    private LocationManager locationManager;
    private ListView satListView;
    private ArrayList<Satellite> satellites;
    private OtherSatelliteAdapter otherSatelliteAdapter;
    private GnssStatus.Callback gnssStatusListener;
    private int minTimeMilliseconds = 10000;
    private int minDistanceMeters = 0;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        getPermissions();
        gnssStatusListener =
                new GnssStatus.Callback() {
                    @Override
                    public void onStarted(){
                    }
                    @Override
                    public void onStopped() {
                    }
                    @Override
                    public void onFirstFix(int ttff) {
                    }
                    @Override
                    public void onSatelliteStatusChanged(GnssStatus status) {
                        int satelliteCount = status.getSatelliteCount();
                        satellites.clear();
                        for (int i = 0; i < satelliteCount; i++) {
                            satellites.add(new Satellite(status.getAzimuthDegrees(i),
                                    status.getCn0DbHz(i),
                                    status.getConstellationType(i),
                                    status.getElevationDegrees(i),
                                    status.getSvid(i)));
                           // satDetailsAdapter.notifyDataSetChanged();
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                };

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.registerGnssStatusCallback(gnssStatusListener, null);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, minTimeMilliseconds, minDistanceMeters, (LocationListener) this
            );
        } catch (SecurityException e) {
            Log.e(MAIN_TAG,"Got security exception.");
            Log.e(MAIN_TAG,e.getMessage());
        } catch (NullPointerException e) {
            Log.e(MAIN_TAG,e.getMessage());
        }

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        satellites = new ArrayList<Satellite>();
        mAdapter = new OtherSatelliteAdapter(satellites);
        recyclerView.setAdapter(mAdapter);
    }

    public void getPermissions() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        locationManager.unregisterGnssStatusCallback(gnssStatusListener);
    }
}