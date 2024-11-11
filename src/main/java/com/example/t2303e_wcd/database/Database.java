package com.example.t2303e_wcd.database;

import java.sql.*;

public class Database {
    private final String connectionString = "jdbc:mysql://localhost:3306/t2303e_wcd";
    private final String user = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn;
    private static Database _instance;
    private Database(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectionString,user,password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static synchronized Database createInstance() {
        if (_instance == null) {
            _instance = new Database();
        }
        return _instance;
    }

    public Statement getStatement() throws SQLException {
        if(conn == null || conn.isClosed()){
            conn = DriverManager.getConnection(connectionString,user,password);
            return conn.createStatement();
        } else {
            return conn.createStatement();
        }
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }
}
