package com.example.t2303e_wcd.entity;

public class Subject {
    private int id;
    private String name;
    private int hours;

    // Constructor
    public Subject(int id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }
    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    // Default constructor (optional)
    public Subject() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                '}';
    }
}
