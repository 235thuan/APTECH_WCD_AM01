package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.ClassDAO;
import com.example.t2303e_wcd.entity.StudentClass;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "ClassCreateController", value = "/class/create")
public class ClassCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        // Forward the request to the create.jsp form page
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        dispatcher.forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        StudentClass studentClass = new StudentClass(name);
        ClassDAO cd = new ClassDAO();
        boolean isCreated = cd.create(studentClass);
        if (isCreated) {
            res.sendRedirect(req.getContextPath() + "/student"); // Use `req.getContextPath()` for flexibility
        } else {
            // If there was an error, forward to the create page with an error message
            req.setAttribute("message", "Failed to create class.");
            req.getRequestDispatcher("/WEB-INF/views/class/create.jsp").forward(req, res); // Ensure correct path to JSP
        }

    }
}
