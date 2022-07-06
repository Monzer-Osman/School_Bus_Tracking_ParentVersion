package com.project.SchoolBusApp.model;

public class FeedBack {
    int rate;
    String title;
    String feedBack;

    public FeedBack(int rate, String title, String feedBack) {
        this.rate = rate;
        this.title = title;
        this.feedBack = feedBack;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
