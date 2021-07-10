package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public HashMap<String, Object> getDetail(Book book) {

        ArrayList<Book> books = new ArrayList<>();
        books = bookMapper.getDetail(book.getId());

        HashMap<String, Object> data = new HashMap<>();
        data.put("bookDetail", books);
        return data;
    }
}
