package com.project.SchoolBusApp.model;

import android.util.Log;

public class Loc {
    double lon;
    double lat;
    String location;


    public Loc(double lang, double lat) {
        this.lon = lang;
        this.lat = lat;
    }

    public Loc(String location){
        this.location = location;
        fromString();
    }
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    private void fromString() {
        if (this.location == null) {
            this.lat = this.lon = 0.0;
        }
        else {
            if (this.location.equals("")) {
                this.lat = this.lon = 0.0;
            }
            else {
                //now try to parse
                String temp = this.location;
                try {
                    String lat = temp.substring(0, temp.indexOf(","));
                    String lon = temp.substring(temp.indexOf(",") + 1);

                    this.lat = Double.parseDouble(lat);
                    this.lon = Double.parseDouble(lon);

                } catch (Exception e) {
                    Log.e("error : ","error in parsing location string :" + temp);
                    this.lat = this.lon = 0.0;
                }
            }
        }
    }

    public Boolean isValid() {
        if (this.lat == 0.0 || this.lon == 0.0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String toString() {
        return String.valueOf(this.lat) + "," + String.valueOf(this.lon);
    }
}
