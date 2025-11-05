package com.sach.service;

public interface IUser {
    boolean login(String name, String password);
    void register(String name, String password ,String email);
    String forgetPassword(String email);
}
