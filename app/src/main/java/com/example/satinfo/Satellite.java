package com.example.satinfo;

import android.location.GnssStatus;

class Satellite {
    public float azimuth;
    public float cn0DHzs;
    public int constellationType;
    public float elevation;
    public int svid;
    public static final String SVID_TAG = "svid";
    public static final String AZIMUTH_TAG = "azimuth";
    public static final String CN0DHZS_TAG = "cn0DHzs";
    public static final String CONSTELLATION_TAG = "constellation";
    public static final String ELEVATION_TAG = "elevation";

    public Satellite(float azimuth, float cn0DHzs, int constellationType,
                     float elevation, int svid) {
        this.azimuth = azimuth;
        this.cn0DHzs = cn0DHzs;
        this.constellationType = constellationType;
        this.elevation = elevation;
        this.svid = svid;
    }

    public String toString() {
        String s = "[";
        s +=String.format("svid: %d,",svid);
        s += String.format(" azimuth: %f,",azimuth);
        s += String.format(" cn0DHzs: %f,",cn0DHzs);
        s += String.format(" constellation: %d,",constellationType);
        s += String.format(" elevation: %f",elevation);
        s += "]";
        return s;
       // return svidStr+azimuthStr+cn0DHzsStr+constellationStr+elevationStr;
    }
}
