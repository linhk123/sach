package com.sach.entity;

public class Da_doc {
    private int id;
    private int id_users;
    private int id_bookss;

    public Da_doc(int id, int id_users, int id_bookss) {
        this.id = id;
        this.id_users = id_users;
        this.id_bookss = id_bookss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public int getId_bookss() {
        return id_bookss;
    }

    public void setId_bookss(int id_bookss) {
        this.id_bookss = id_bookss;
    }
}
