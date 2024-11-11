package com.example.t2303e_wcd.entity;

public class StudentSubject {
    private int studentId;
    private int subjectId ;

    public StudentSubject(int studentId, int subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "StudentSubject{" +
                "studentId=" + studentId +
                ", subjectId=" + subjectId +
                '}';
    }
}
