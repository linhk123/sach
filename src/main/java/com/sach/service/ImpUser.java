package com.sach.service;


import com.sach.config.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpUser implements IUser {
    private final Connection conn;
    public ImpUser() {
        conn = MySqlConnector.getConnection();
    }

    @Override
    public boolean login(String name, String password) {
        String sql = "select * from user where name = ? and password = ?";
        try {
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, password);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Đăng nhập thành công, vào trang home");
                return true;
            }else {
                System.out.println("Sai tên hoặc mật khẩu, quay lại trang login");
                return false;
            }
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

    @Override
    public void register(String name, String password, String email) {
        String sql = "insert into user (name, password, email) values (?, ?, ?)";
        try {
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, email);
            prepareStatement.setString(3, password);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public String forgetPassword(String email) {
        String sql = "Select * from user where email = ?";
        try {
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setString(1, email);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                prepareStatement.close();
                resultSet.close();
                return password;
            }
            prepareStatement.close();
            resultSet.close();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
