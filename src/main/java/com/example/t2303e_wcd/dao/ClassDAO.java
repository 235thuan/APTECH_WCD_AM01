package com.example.t2303e_wcd.dao;

import com.example.t2303e_wcd.database.Database;
import com.example.t2303e_wcd.entity.Student;
import com.example.t2303e_wcd.entity.StudentClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements DAOInterface<StudentClass> {
    @Override
    public List<StudentClass> all() {
        String sql = "SELECT * FROM classes ORDER BY id";
        List<StudentClass> studentClasses = new ArrayList<>();
        try (
                Connection conn = Database.createInstance().getStatement().getConnection(); // Get a new connection directly
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                // Create a Student object
                studentClasses.add(new StudentClass(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Use proper logging
        }
        return studentClasses;

    }

    @Override
    public boolean create(StudentClass studentClass) {
        // SQL statement to insert a student, including `class_id` if necessary
        String sql = "INSERT INTO classes (name) VALUES (?)";

        try (
                // Create a new connection from the Database instance
                Connection conn = Database.createInstance().getStatement().getConnection();
                // Use PreparedStatement to prevent SQL injection
                PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            // Bind the parameters to the SQL statement
            statement.setString(1, studentClass.getName());

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
    public boolean update(StudentClass studentClass) {
        return false;
    }

    @Override
    public boolean delete(StudentClass studentClass) {
        String sql = "DELETE FROM classes WHERE id = ?";
        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentClass.getId()); // Ensure we're deleting the student with the provided ID

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Return true if exactly one student is deleted
        } catch (SQLException e) {
            System.out.println("lỗi truy vấn xóa : "+e.getMessage());
            return false;  // Return false in case of error
        }
    }

    @Override
    public <K> StudentClass find(K id) {
        // Câu lệnh SQL để tìm sinh viên dựa trên ID
        String sql = "SELECT * FROM classes WHERE id = ?";

        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Thiết lập tham số ID
            pstmt.setInt(1, (Integer) id);  // Chuyển đổi từ kiểu K (id) thành kiểu Integer

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = pstmt.executeQuery();

            // Nếu tìm thấy sinh viên, trả về đối tượng Student
            if (rs.next()) {
                return new StudentClass(
                        rs.getInt("id"),
                        rs.getString("name")
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
