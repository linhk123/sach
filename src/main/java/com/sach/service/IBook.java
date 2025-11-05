package com.sach.service;

import com.sach.entity.book;

import java.util.List;

public interface IBook {
    List<book> getAllBooks();
    book getBookId(int id_book);
    List<book> searchBooks(String keyword);
}
