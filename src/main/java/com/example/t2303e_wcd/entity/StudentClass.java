package com.example.t2303e_wcd.entity;

public class StudentClass {
    private int id;
    private String name;

    // Constructor
    public StudentClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public StudentClass( String name) {
        this.name = name;
    }

    // Default constructor
    public StudentClass() {
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

    @Override
    public String toString() {
        return "StudentClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

