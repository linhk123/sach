package com.sach.service;

import com.sach.entity.book;

import java.util.List;

public interface IThich {
    void addFavorite(int id_user,int id_books);
    void removeFavorite(int id_user, int id_books);
    List<book> getFavorites(int id_user);
    boolean isFavorite(int id_user, int id_books);
}
