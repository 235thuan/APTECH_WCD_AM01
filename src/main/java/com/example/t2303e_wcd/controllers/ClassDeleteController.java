package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.ClassDAO;
import com.example.t2303e_wcd.dao.StudentDAO;
import com.example.t2303e_wcd.entity.Student;
import com.example.t2303e_wcd.entity.StudentClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ClassDeleteController", value = "/class/delete")
public class ClassDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get the studentId from the request parameter
        int classId;
        try {
            classId = Integer.parseInt(req.getParameter("id")); // Use "id" to get the studentId
        } catch (NumberFormatException e) {
            // Handle the case where the id parameter is not valid
            req.setAttribute("message", "Invalid student ID.");
            res.sendRedirect("/T2303E_WCD_war/class");
            return;
        }

        // Call DAO to find the student by id
        ClassDAO sd = new ClassDAO();
        StudentClass studentClass = sd.find(classId); // Retrieve the student by studentId

        if (studentClass != null) {
            // Now delete the student if it exists
            boolean isDeleted = sd.delete(studentClass); // Pass the Student object to delete

            if (isDeleted) {
                // Redirect back to the student listing page after successful deletion
                res.sendRedirect("/T2303E_WCD_war/class");
            } else {
                // Redirect back with an error message if deletion failed
                req.setAttribute("message", "Xóa lớp học thất bại.");
                res.sendRedirect("/T2303E_WCD_war/class");
            }
        } else {
            // If no student found, redirect to the list page with a message
            req.setAttribute("message", "Không tìm thấy lơp.");
            res.sendRedirect("/T2303E_WCD_war/class");
        }
    }
}
