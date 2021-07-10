package com.bjtu.bookstore.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.Store;
import com.bjtu.bookstore.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public interface CartService {
    HashMap<String, Object> getAllCart(User user);

    int calculate(ArrayList<Book> books);

    int addtocart(Cart cart);

    int deletefromcart(Cart cart);

    int modifyNumFromCart(Cart cart);
}
