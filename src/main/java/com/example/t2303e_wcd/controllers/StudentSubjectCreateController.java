package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.StudentSubjectDAO;
import com.example.t2303e_wcd.dao.SubjectDAO;
import com.example.t2303e_wcd.entity.StudentSubject;
import com.example.t2303e_wcd.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StudentSubjectCreateController",value = "/studentSubject/create")
public class StudentSubjectCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        // Forward the request to the create.jsp form page
        RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
        dispatcher.forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        int student_id = Integer.parseInt(req.getParameter("student_id"));
        int subject_id = Integer.parseInt(req.getParameter("subject_id"));
        StudentSubject studentSubject = new StudentSubject(student_id,subject_id);
        StudentSubjectDAO ssd = new StudentSubjectDAO();
        boolean isCreated = ssd.create(studentSubject);
        if (isCreated) {
            res.sendRedirect(req.getContextPath() + "/student"); // Use `req.getContextPath()` for flexibility
        } else {
            // If there was an error, forward to the create page with an error message
            req.setAttribute("message", "Thêm mới thất bại .");
            req.getRequestDispatcher("/WEB-INF/views/studentSubject/create.jsp").forward(req, res); // Ensure correct path to JSP
        }

    }
}
