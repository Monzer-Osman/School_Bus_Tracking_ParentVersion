package com.project.SchoolBusApp.model;

public class Loc {
    double lang;
    double lat;

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Loc(double lang, double lat) {
        this.lang = lang;
        this.lat = lat;
    }
}
