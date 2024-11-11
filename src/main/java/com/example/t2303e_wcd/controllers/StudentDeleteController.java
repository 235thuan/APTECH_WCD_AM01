package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.StudentDAO;
import com.example.t2303e_wcd.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentDeleteController", value = "/student/delete")
public class StudentDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get the studentId from the request parameter
        int studentId;
        try {
            studentId = Integer.parseInt(req.getParameter("id")); // Use "id" to get the studentId
        } catch (NumberFormatException e) {
            // Handle the case where the id parameter is not valid
            req.setAttribute("message", "Invalid student ID.");
            res.sendRedirect("/T2303E_WCD_war/student");
            return;
        }

        // Call DAO to find the student by id
        StudentDAO sd = new StudentDAO();
        Student student = sd.find(studentId); // Retrieve the student by studentId

        if (student != null) {
            // Now delete the student if it exists
            boolean isDeleted = sd.delete(student); // Pass the Student object to delete

            if (isDeleted) {
                // Redirect back to the student listing page after successful deletion
                res.sendRedirect("/T2303E_WCD_war/student");
            } else {
                // Redirect back with an error message if deletion failed
                req.setAttribute("message", "Failed to delete student.");
                res.sendRedirect("/T2303E_WCD_war/student");
            }
        } else {
            // If no student found, redirect to the list page with a message
            req.setAttribute("message", "Student not found.");
            res.sendRedirect("/T2303E_WCD_war/student");
        }
    }
}
