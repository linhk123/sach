package com.sach.service;

import com.sach.entity.book;

import java.util.List;

public interface IReading {
    void addReading(int id_users, int id_bookss);
    List<book> getReadingByUser(int id_users);
    boolean getReading(int id_users, int id_bookss);
}
