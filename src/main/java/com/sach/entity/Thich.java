package com.sach.entity;

public class Thich {
    private int id_L;
    private int id_user;
    private int id_books;

    public Thich(int id_L, int id_user, int id_books) {
        this.id_L = id_L;
        this.id_user = id_user;
        this.id_books = id_books;
    }

    public int getId_L() {
        return id_L;
    }

    public void setId_L(int id_L) {
        this.id_L = id_L;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_books() {
        return id_books;
    }

    public void setId_books(int id_books) {
        this.id_books = id_books;
    }
}
