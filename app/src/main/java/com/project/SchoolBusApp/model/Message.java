package com.project.SchoolBusApp.model;

public class Message {
    int id;
    String title;
    String message;

    public Message(int id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
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
