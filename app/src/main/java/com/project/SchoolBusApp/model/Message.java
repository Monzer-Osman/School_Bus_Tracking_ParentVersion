package com.project.SchoolBusApp.model;

import java.util.Date;

public class Message {
    int id;
    String title;
    String message;
    Date time;

    public String getTime() {
        return time.toString();
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Message(int id, String title, String message, Date time) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
