package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;

import java.util.HashMap;

public interface UserService {

    HashMap<String, String> userLogin(User user);

    void register(User user);

    void forgetPassword(User user);

    void modifyPassword(User user);

    User getInformation(User uid);//通过用户id获取用户信息

    void modifyInformation(User user); //通过用户id更新用户信息

    public HashMap<String, Object> getRightUsers(User user);

    public void changeUserState(User user);
}
