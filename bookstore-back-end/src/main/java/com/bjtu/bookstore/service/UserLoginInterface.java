package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.User;

import java.util.HashMap;

public interface UserLoginInterface {
    HashMap<String, String> userLogin(User user);
}
