package com.project.SchoolBusApp.model;

public class LocationResponse {
    String location;
    String time;
    int user_id;

    public LocationResponse(){};

    public LocationResponse(String location, String time, int user_id) {
        this.location = location;
        this.time = time;
        this.user_id = user_id;
    }

    public LocationResponse(String location, String time) {
        this.location = location;
        this.time = time;
    }

    public String getLoc() {
        return location;
    }

    public void setLoc(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
