package com.sach.entity;

public class book {
    private int id_book;
    private String nameB;
    private String tac_gia;
    private String the_loai;
    private String anh;
    private String link;

    public book(int id_book, String nameB, String tac_gia, String the_loai, String anh, String link) {
        this.id_book = id_book;
        this.nameB = nameB;
        this.tac_gia = tac_gia;
        this.the_loai = the_loai;
        this.anh = anh;
        this.link = link;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public String getThe_loai() {
        return the_loai;
    }

    public void setThe_loai(String the_loai) {
        this.the_loai = the_loai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
