package com.project.SchoolBusApp.model;

public class Kid {

    int id;
    int parent_id;
    int age;
    int level;
    String name;

    public Kid() {
    }

    public Kid(int id, int parent_id,int age, int level, String name) {
        this.id = id;
        this.age = age;
        this.level = level;
        this.name = name;
        this.parent_id = parent_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
