package com.project.SchoolBusApp.ui.login.ui_login;

class LoggedInUserView {
    private String status;
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoggedInUserView(String status) {
        this.status = status;
    }

    public LoggedInUserView(String status, int id, String firstName, String lastName, String phoneNumber, String email) {
        this.status = status;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
//... other data fields that may be accessible to the UI

    public LoggedInUserView(LoggedInUserView loggedInUserView) {
        this.firstName = loggedInUserView.firstName;
        this.lastName = loggedInUserView.lastName;
        this.phoneNumber = loggedInUserView.phoneNumber;
        this.email = loggedInUserView.email;
    }

    public LoggedInUserView(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String getDisplayName() {
        return firstName;
    }
}