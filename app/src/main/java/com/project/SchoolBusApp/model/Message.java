package com.project.SchoolBusApp.model;

import java.util.Date;

public class Message {
    String id;
    String title;
    String message;
    String sender;
    String receiver;
    String date;

    public Message(String id, String title, String message,  String time) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.date = time;
    }

    public Message(String title, String message, String sender, String receiver, String time) {
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = time;
    }

    public Message(String id, String title, String message, String sender, String receiver, String time) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return date.toString();
    }

    public void setTime(String time) {
        this.date = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
