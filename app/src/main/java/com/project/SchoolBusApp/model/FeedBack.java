package com.project.SchoolBusApp.model;

public class FeedBack {
    int rate;
    String feedBack;

    public FeedBack(int rate, String feedBack) {
        this.rate = rate;
        this.feedBack = feedBack;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
