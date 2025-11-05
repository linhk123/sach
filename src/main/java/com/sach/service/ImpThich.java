package com.sach.service;

import com.sach.config.MySqlConnector;
import com.sach.entity.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpThich implements IThich {

    private final Connection conn;
    public ImpThich() {
        conn = MySqlConnector.getConnection();
    }

    @Override
    public void addFavorite(int id_user, int id_books) {
        String sql = "insert into yeu_thich (id_user, id_books) values (?,?)";
        try{
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setInt(1,id_user);
            prepareStatement.setInt(2,id_books);
            prepareStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeFavorite(int id_user, int id_books) {
        String sql ="delete from yeu_thich where id_user = ? and id_books = ?";
        try{
            PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
            prepareStatement.setInt(1,id_user);
            prepareStatement.setInt(2,id_books);
            prepareStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public  List<book> getFavorites(int id_user) {
        List<book> books = new ArrayList<>();
        String sql = "select b.* from book b join yeu_thich y on b.id_book = y.id_books where y.id_user = ?";
        try {
            PreparedStatement preparedStatement =this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id_user);
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
    public boolean isFavorite(int id_user, int id_books) {
        String sql = "select * from yeu_thich where id_user = ? and id_books = ?";
        try {
            PreparedStatement preparedStatement =this.conn.prepareStatement(sql);
            preparedStatement.setInt(1,id_user);
            preparedStatement.setInt(2,id_books);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

