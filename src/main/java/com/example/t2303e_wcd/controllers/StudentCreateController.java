package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.StudentDAO;
import com.example.t2303e_wcd.entity.Student;
import com.example.t2303e_wcd.entity.StudentClass;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentCreateController", value = "/student/create")
public class StudentCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        StudentDAO studentClassDAO = new StudentDAO(); // Assuming you have a DAO for StudentClass
        List<StudentClass> studentClasses = studentClassDAO.getAllClasses(); // Retrieve all classes

        // Set the list as a request attribute
        req.setAttribute("studentClasses", studentClasses);

        // Forward the request to the create.jsp form page
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Retrieve student data from the form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");

        // Optional handling for `class_id` if applicable
        String classIdParam = req.getParameter("class_id");
        int classId = 0; // Default value if no `class_id` is provided or invalid
        if (classIdParam != null && !classIdParam.trim().isEmpty()) {
            try {
                classId = Integer.parseInt(classIdParam);
            } catch (NumberFormatException e) {
                // Log and handle invalid `class_id` input gracefully
                System.err.println("Invalid class ID provided: " + classIdParam);
            }
        }

        // Create a new `StudentClass` object if `class_id` is valid
        StudentClass studentClass = null;
        if (classId > 0) {
            studentClass = new StudentClass();
            studentClass.setId(classId);
        }

        // Create a new Student object with the form data and optional `StudentClass`
        Student student = new Student(name, email, address, telephone, studentClass);

        // Use the StudentDAO to create the new student in the database
        StudentDAO sd = new StudentDAO();
        boolean isCreated = sd.create(student);

        // If the student was created successfully, redirect to the list page
        if (isCreated) {
            res.sendRedirect(req.getContextPath() + "/student"); // Use `req.getContextPath()` for flexibility
        } else {
            // If there was an error, forward to the create page with an error message
            req.setAttribute("message", "Failed to create student.");
            req.getRequestDispatcher("/WEB-INF/views/student/create.jsp").forward(req, res); // Ensure correct path to JSP
        }
    }

}