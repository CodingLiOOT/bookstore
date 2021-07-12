package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CategoryMapper;
import com.bjtu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public HashMap<String, Object> getDetail(Book book) {

        ArrayList<Book> books = new ArrayList<>();
        books = bookMapper.getDetail(book.getId());
        for(int i=0;i<books.size();i++) {
            books.get(i).setCategoryName(categoryMapper.getNameById(books.get(i).getCategoryId()));
        }
        HashMap<String, Object> data = new HashMap<>();
        data.put("bookDetail", books);
        return data;
    }
}
