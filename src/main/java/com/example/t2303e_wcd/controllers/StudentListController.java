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

@WebServlet(name = "Student List",value = "/student")
public class StudentListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{
        // lấy ds sinh viên từ DB
        StudentDAO sd = new StudentDAO();
        List<Student> list = sd.all();
        // trả về 1 giao diện danh sách sinh viên
        req.setAttribute("students",list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(req,res);
    }

}
