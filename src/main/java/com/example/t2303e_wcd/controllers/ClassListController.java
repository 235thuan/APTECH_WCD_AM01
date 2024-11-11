package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.ClassDAO;

import com.example.t2303e_wcd.dao.StudentDAO;
import com.example.t2303e_wcd.entity.StudentClass;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ClassListController", value = "/class/list")
public class ClassListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // lấy ds sinh viên từ DB
        ClassDAO cd = new ClassDAO();
        List<StudentClass> classList = cd.all();
        StudentDAO sd = new StudentDAO();
        Map<Integer, Integer> studentCounts = new HashMap<>();
        try {
            for (StudentClass sc : classList) {
                int count = sd.countStudentsInClass(sc.getId());
                studentCounts.put(sc.getId(), count);
            }
            req.setAttribute("classes", classList);
            req.setAttribute("studentCounts", studentCounts);
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi lấy dữ liệu : " + e.getMessage());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("list.jsp");
        dispatcher.forward(req,res);
    }
}
