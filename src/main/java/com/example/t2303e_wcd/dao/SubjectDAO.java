package com.example.t2303e_wcd.dao;

import com.example.t2303e_wcd.database.Database;
import com.example.t2303e_wcd.entity.StudentClass;
import com.example.t2303e_wcd.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectDAO implements DAOInterface<Subject> {
    @Override
    public List<Subject> all() {
        return List.of();
    }

    @Override
    public boolean create(Subject subject) {
        // SQL statement to insert a student, including `class_id` if necessary
        String sql = "INSERT INTO subjects (name,hours) VALUES (?,?)";

        try (
                // Create a new connection from the Database instance
                Connection conn = Database.createInstance().getStatement().getConnection();
                // Use PreparedStatement to prevent SQL injection
                PreparedStatement statement = conn.prepareStatement(sql)
        ) {
            // Bind the parameters to the SQL statement
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getHours());

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
    public boolean update(Subject subject) {
        return false;
    }

    @Override
    public boolean delete(Subject subject) {
        return false;
    }

    @Override
    public <K> Subject find(K id) {
        // Câu lệnh SQL để tìm sinh viên dựa trên ID
        String sql = "SELECT * FROM subjects WHERE id = ?";

        try (
                Connection conn = Database.createInstance().getStatement().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Thiết lập tham số ID
            pstmt.setInt(1, (Integer) id);  // Chuyển đổi từ kiểu K (id) thành kiểu Integer

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = pstmt.executeQuery();

            // Nếu tìm thấy sinh viên, trả về đối tượng Student
            if (rs.next()) {
                return new Subject(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("hours")
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
