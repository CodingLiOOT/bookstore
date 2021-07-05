package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.User;

import java.util.HashMap;

public interface UserService {

    HashMap<String, String> userLogin(User user);

    void register(User user);

    void forgetPassword(User user);

    void modifyPassword(User user);
}
