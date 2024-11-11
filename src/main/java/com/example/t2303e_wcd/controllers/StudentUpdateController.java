package com.example.t2303e_wcd.controllers;

import com.example.t2303e_wcd.dao.ClassDAO;
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

@WebServlet(value = "/student/update")
public class StudentUpdateController extends HttpServlet {

    // Hiển thị form cập nhật thông tin sinh viên (Yêu cầu GET)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Lấy ID của sinh viên từ tham số trên URL
        int id = Integer.parseInt(req.getParameter("id"));

        // Lấy thông tin sinh viên từ cơ sở dữ liệu
        StudentDAO sd = new StudentDAO();
        Student student = sd.find(id);
        List<StudentClass> studentClasses = sd.getAllClasses();
        req.setAttribute("studentClasses", studentClasses);
        // Nếu tìm thấy sinh viên, chuyển dữ liệu sang form cập nhật
        if (student != null) {
            req.setAttribute("student", student);
            // Chuyển tiếp tới trang cập nhật thông tin sinh viên
            RequestDispatcher dispatcher = req.getRequestDispatcher("update.jsp"); // Corrected path
            dispatcher.forward(req, res);
        } else {
            // Nếu không tìm thấy sinh viên, chuyển hướng về trang danh sách sinh viên và thông báo lỗi
            req.setAttribute("message", "Không tìm thấy sinh viên.");
            res.sendRedirect(req.getContextPath() + "/student");
        }
    }

    // Xử lý việc gửi form để cập nhật thông tin sinh viên (Yêu cầu POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Lấy thông tin sinh viên từ form
        int id = Integer.parseInt(req.getParameter("id"));  // Retrieves the student ID from the form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");

        String classIdParam = req.getParameter("class_id");
        System.out.println("class id =" + classIdParam);
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
            ClassDAO cd = new ClassDAO();
            studentClass = cd.find(classId);
        }


        // Create a new Student object with the form data and optional `StudentClass`
        Student student = new Student(id,name, email, address, telephone, studentClass);
        System.out.println("student class id : "+student.getStudentClass().getId());

        // Sử dụng StudentDAO để cập nhật sinh viên vào cơ sở dữ liệu
        StudentDAO sd = new StudentDAO();
        boolean isUpdated = sd.update(student);

        // Nếu cập nhật thành công, chuyển hướng về trang danh sách sinh viên
        if (isUpdated) {
            res.sendRedirect(req.getContextPath() + "/student"); // Using context path
        } else {
            System.out.println("loi update");
            // Nếu cập nhật không thành công, hiển thị thông báo lỗi và giữ lại thông tin đã nhập trong form
            req.setAttribute("message", "Cập nhật sinh viên thất bại.");
            req.setAttribute("student", student); // Giữ lại thông tin sinh viên để người dùng có thể sửa lại
            RequestDispatcher dispatcher = req.getRequestDispatcher("/student/update.jsp"); // Corrected path
            dispatcher.forward(req, res);
        }
    }

}
