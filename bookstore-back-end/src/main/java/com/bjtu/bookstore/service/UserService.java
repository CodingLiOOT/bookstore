package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.User;

public interface UserService {

    String userLogin(User user);

    void register(User user);

    void forgetPassword(User user);
}
