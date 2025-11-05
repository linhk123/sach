package com.sach.service;

import com.sach.config.MySqlConnector;
import com.sach.entity.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpReading implements IReading{

    private final Connection conn;
    public ImpReading() {
        conn = MySqlConnector.getConnection();
    }
    @Override
    public void addReading(int id_users, int id_bookss) {
        String sql = "insert into da_doc (id_users, id_bookss) values (?,?)";
        try{
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setInt(1,id_users);
            prepareStatement.setInt(2,id_bookss);
            prepareStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public List<book> getReadingByUser(int id_users) {
        List<book> books = new ArrayList<>();
        String sql = "select b.* from book b join da_doc d on b.id_book = d.id_bookss where d.id_users = ?";
        try {
            PreparedStatement preparedStatement =this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id_users);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                book book= new book(
                        resultSet.getInt("id_book"),
                        resultSet.getString("nameB"),
                        resultSet.getString("tac_gia"),
                        resultSet.getString("the_loai"),
                        resultSet.getString("anh"),
                        resultSet.getString("link") );
                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean getReading(int id_users, int id_bookss) {
        String sql = "select * from da_doc where id_users = ? and id_bookss = ?";
        try {
            PreparedStatement preparedStatement =this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id_users);
            preparedStatement.setInt(2,id_bookss);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void markAsRead(int userId, int bookId) {
        String sql = "INSERT INTO reading(user_id, book_id) VALUES(?, ?) ON DUPLICATE KEY UPDATE book_id=book_id";
        try {
            PreparedStatement preparedStatement =this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
