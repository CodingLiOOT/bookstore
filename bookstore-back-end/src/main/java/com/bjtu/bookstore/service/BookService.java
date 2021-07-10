package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;

import java.util.HashMap;

public interface BookService {
    public HashMap<String, Object> getDetail(Book book);
}
