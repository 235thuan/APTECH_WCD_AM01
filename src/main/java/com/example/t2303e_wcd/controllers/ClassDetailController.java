package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.StudentDAO;
import com.example.t2303e_wcd.entity.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

@WebServlet(name = "ClassDetailController", value = "/class/detail")
public class ClassDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        StudentDAO sd = new StudentDAO();
        try {
            int classId = Integer.parseInt(req.getParameter("class_id"));
            List<Student> listStudent = sd.allSameClass(classId);

            req.setAttribute("studentSameClass", listStudent);
            RequestDispatcher dispatcher = req.getRequestDispatcher("detail.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            req.setAttribute("message", "Lỗi " + e.getMessage());
        }
    }
}

