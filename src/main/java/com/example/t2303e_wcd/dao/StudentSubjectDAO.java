package com.example.t2303e_wcd.dao;

import com.example.t2303e_wcd.database.Database;
import com.example.t2303e_wcd.entity.Student;
import com.example.t2303e_wcd.entity.StudentSubject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentSubjectDAO implements DAOInterface<StudentSubject>{
    @Override
    public List<StudentSubject> all() {
        return List.of();
    }

    @Override
    public boolean create(StudentSubject studentSubject) {
// SQL statement to insert a student, including `class_id` if necessary
        String sql = "INSERT INTO student_subjects (student_id,subject_id) VALUES (?, ?)";

        try (
                // Create a new connection from the Database instance
                Connection conn = Database.createInstance().getStatement().getConnection();
                // Use PreparedStatement to prevent SQL injection
                PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            // Bind the parameters to the SQL statement
            statement.setInt(1, studentSubject.getStudentId());
            statement.setInt(2, studentSubject.getSubjectId());
            // Execute the statement
            int rowsAffected = statement.executeUpdate();

            // Return true if at least one row was inserted
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Log SQL errors
            System.err.println("Lỗi khi thực hiện câu lệnh SQL: " + e.getMessage());
            return false; // Return false on error

        } catch (Exception e) {
            // Handle any non-SQL exceptions
            System.err.println("Lỗi không xác định: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(StudentSubject studentSubject) {
        return false;
    }

    @Override
    public boolean delete(StudentSubject studentSubject) {
        return false;
    }

    @Override
    public <K> StudentSubject find(K id) {
        return null;
    }


    public <K> StudentSubject findBaseStudentId(K studentId) {
        String sql = "SELECT * FROM student_subjects WHERE student_id = ?";

        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Thiết lập tham số ID
            pstmt.setInt(1, (Integer) studentId);  // Chuyển đổi từ kiểu K (id) thành kiểu Integer

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = pstmt.executeQuery();

            // Nếu tìm thấy sinh viên, trả về đối tượng Student
            if (rs.next()) {
                return new StudentSubject(
                        rs.getInt("student_id"),
                        rs.getInt("subject_id")
                );
            } else {
                return null;  // Trả về null nếu không tìm thấy sinh viên
            }

        } catch (SQLException e) {
            e.printStackTrace();  // In thông báo lỗi nếu có ngoại lệ xảy ra
            return null;  // Trả về null nếu có lỗi xảy ra
        }
    }
}
