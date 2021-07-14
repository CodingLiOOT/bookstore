package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Lunbo;
import com.bjtu.bookstore.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface LunboService {
    HashMap<String, Object> getAllLunbos();

    void addtolunbo(Lunbo lunbo);

    int deletefromlunbo(Lunbo lunbo);
}
