package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.entity.User;
import com.google.common.base.Preconditions;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface BookService {
    public HashMap<String, Object> getDetail(Book book);
    public HashMap<String, Object> getAllBooks(Cart cart);
}
