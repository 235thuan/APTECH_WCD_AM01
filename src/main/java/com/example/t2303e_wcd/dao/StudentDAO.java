package com.example.t2303e_wcd.dao;

import com.example.t2303e_wcd.database.Database;
import com.example.t2303e_wcd.entity.Student;
import com.example.t2303e_wcd.entity.StudentClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student> {
    @Override
    public List<Student> all() {
        String sql = "SELECT * FROM students";
        List<Student> list = new ArrayList<>();
        try (
                Connection conn = Database.createInstance().getStatement().getConnection(); // Get a new connection directly
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                int classId = rs.getInt("class_id"); // Retrieve class_id
                StudentClass studentClass = null;
                if (classId > 0) {
                    // Fetch class details if necessary (example query)
                    String classSql = "SELECT * FROM classes WHERE id = ?";
                    try (PreparedStatement classStmt = conn.prepareStatement(classSql)) {
                        classStmt.setInt(1, classId);
                        ResultSet classRs = classStmt.executeQuery();
                        if (classRs.next()) {
                            studentClass = new StudentClass();
                            studentClass.setId(classRs.getInt("id"));
                            studentClass.setName(classRs.getString("name")); // Assuming `name` column exists in `classes` table
                        }
                    }
                }
                // Create a Student object
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        studentClass // Pass the StudentClass object
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging
        }
        return list;
    }


    public boolean create(Student student) {
        // SQL statement to insert a student, including `class_id` if necessary
        String sql = "INSERT INTO students (name, email, address, telephone, class_id) VALUES (?, ?, ?, ?, ?)";

        try (
                // Create a new connection from the Database instance
                Connection conn = Database.createInstance().getStatement().getConnection();
                // Use PreparedStatement to prevent SQL injection
                PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            // Bind the parameters to the SQL statement
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getTelephone());

            // Check if `StudentClass` is not null before setting `class_id`
            if (student.getStudentClass() != null && student.getStudentClass().getId() != 0) {
                statement.setInt(5, student.getStudentClass().getId());
            } else {
                statement.setNull(5, java.sql.Types.INTEGER); // Set `class_id` as NULL if not available
            }

            // Execute the statement
            int rowsAffected = statement.executeUpdate();

            // Return true if at least one row was inserted
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log SQL errors
            System.err.println("Lỗi khi thực hiện câu lệnh SQL: " + e.getMessage());
            e.printStackTrace();
            return false; // Return false on error

        } catch (Exception e) {
            // Handle any non-SQL exceptions
            System.err.println("Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Student student) {
        // Câu lệnh SQL để cập nhật thông tin của sinh viên
        String sql = "UPDATE students SET name = ?, email = ?, address = ?, telephone = ?, class_id = ? WHERE id = ?";

        // Sử dụng try-with-resources để tự động đóng kết nối và PreparedStatement
        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Thiết lập các tham số cho câu lệnh SQL
            pstmt.setString(1, student.getName());      // Thiết lập tên sinh viên
            pstmt.setString(2, student.getEmail());     // Thiết lập email của sinh viên
            pstmt.setString(3, student.getAddress());   // Thiết lập địa chỉ của sinh viên
            pstmt.setString(4, student.getTelephone());
            pstmt.setInt(5, student.getStudentClass().getId());
            pstmt.setInt(6, student.getId());           // Thiết lập ID của sinh viên để tìm và cập nhật thông tin
            // Thực thi câu lệnh SQL để cập nhật thông tin sinh viên
            int rowsUpdated = pstmt.executeUpdate();

            // Nếu số dòng được cập nhật lớn hơn 0, có nghĩa là cập nhật thành công
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();  // In thông báo lỗi nếu có ngoại lệ xảy ra
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }

    public boolean delete(Student student) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, student.getId()); // Ensure we're deleting the student with the provided ID

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Return true if exactly one student is deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of error
        }
    }

    @Override
    public <K> Student find(K id) {
        // Câu lệnh SQL để tìm sinh viên dựa trên ID
        String sql = "SELECT * FROM students WHERE id = ?";

        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Thiết lập tham số ID
            pstmt.setInt(1, (Integer) id);  // Chuyển đổi từ kiểu K (id) thành kiểu Integer

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = pstmt.executeQuery();

            // Nếu tìm thấy sinh viên, trả về đối tượng Student
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        rs.getInt("class_id")
                );
            } else {
                return null;  // Trả về null nếu không tìm thấy sinh viên
            }

        } catch (SQLException e) {
            e.printStackTrace();  // In thông báo lỗi nếu có ngoại lệ xảy ra
            return null;  // Trả về null nếu có lỗi xảy ra
        }
    }

    public List<StudentClass> getAllClasses() {
        List<StudentClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                StudentClass studentClass = new StudentClass();
                studentClass.setId(rs.getInt("id"));
                studentClass.setName(rs.getString("name"));
                classes.add(studentClass);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging in production
        }
        return classes;
    }

}
