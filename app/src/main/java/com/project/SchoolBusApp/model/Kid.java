package com.project.SchoolBusApp.model;

public class Kid {
    int id;
    int age;
    int level;
    String firstName;
    String LastName;

    public Kid() {
    }

    public Kid(int id, int age, int level, String firstName, String lastName) {
        this.id = id;
        this.age = age;
        this.level = level;
        this.firstName = firstName;
        LastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
