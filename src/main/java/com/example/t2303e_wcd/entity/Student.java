package com.example.t2303e_wcd.entity;
//lop 1 n sv
//        sv n n mon
public class Student {
    private int id;
    private String name;
    private String email;
    private String address;
    private String telephone;
    private StudentClass studentClass; // Updated to use StudentClass

    // Constructor
    public Student(int id, String name, String email, String address, String telephone, StudentClass studentClass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.studentClass = studentClass;
    }
    public Student( String name, String email, String address, String telephone, StudentClass studentClass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.studentClass = studentClass;
    }

    // Default constructor
    public Student() {
    }

    public Student(int id, String name, String email, String address, String telephone, int classId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        if (classId > 0) {
            this.studentClass = new StudentClass();
            this.studentClass.setId(classId);
        } else {
            this.studentClass = null; // Or handle as per your logic
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", studentClass=" + (studentClass != null ? studentClass.getName() : "null") +
                '}';
    }
}

