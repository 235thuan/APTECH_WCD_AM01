package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.ClassDAO;
import com.example.t2303e_wcd.dao.SubjectDAO;
import com.example.t2303e_wcd.entity.StudentClass;
import com.example.t2303e_wcd.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SubjectCreateController", value = "/subject/create")
public class SubjectCreateController extends HttpServlet {
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
        int hours = Integer.parseInt(req.getParameter("hours"));
        Subject subject = new Subject(name,hours);
        SubjectDAO sd = new SubjectDAO();
        boolean isCreated = sd.create(subject);
        if (isCreated) {
            res.sendRedirect(req.getContextPath() + "/student"); // Use `req.getContextPath()` for flexibility
        } else {
            // If there was an error, forward to the create page with an error message
            req.setAttribute("message", "Failed to create subject.");
            req.getRequestDispatcher("/WEB-INF/views/subject/create.jsp").forward(req, res); // Ensure correct path to JSP
        }

    }
}
