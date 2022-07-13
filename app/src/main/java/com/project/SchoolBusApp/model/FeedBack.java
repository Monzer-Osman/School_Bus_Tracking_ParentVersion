package com.project.SchoolBusApp.model;

public class FeedBack {
    int id;
    int user_id;
    float rate;
    String feed;

    public FeedBack(int user_id, float number_stars, String comment) {
        this.user_id = user_id;
        this.rate = number_stars;
        this.feed = comment;
    }

    public FeedBack(int rate, String feedBack) {
        this.rate = rate;
        this.feed = feedBack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }
}
