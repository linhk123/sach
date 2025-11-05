package com.sach.service;

import com.sach.config.MySqlConnector;
import com.sach.entity.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpBook implements IBook {
    private final Connection conn;
    public ImpBook() {
        conn = MySqlConnector.getConnection();
    }

    @Override
    public  List<book> getAllBooks() {
        String sql = "select * from book";
        List<book> books = new ArrayList<book>();
        try{
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                book book= new book(
                        resultSet.getInt("id_book"),
                        resultSet.getString("nameB"),
                        resultSet.getString("tac_gia"),
                        resultSet.getString("the_loai"),
                        resultSet.getString("anh"),
                        resultSet.getString("link")
                );
                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public book getBookId(int id_book) {
        String sql ="Select * from book where id_book = ?";
        book books = null;
        try{
            PreparedStatement preparedStatement=this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id_book);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                books = new book(
                        resultSet.getInt("id_book"),
                        resultSet.getString("nameB"),
                        resultSet.getString("tac_gia"),
                        resultSet.getString("the_loai"),
                        resultSet.getString("anh"),
                        resultSet.getString("link")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }
    public List<book> searchBooks(String keyword) {
        List<book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE LOWER(nameB) LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new book(
                        rs.getInt("id_book"),
                        rs.getString("nameB"),
                        rs.getString("tac_gia"),
                        rs.getString("the_loai"),
                        rs.getString("anh"),
                        rs.getString("link")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
