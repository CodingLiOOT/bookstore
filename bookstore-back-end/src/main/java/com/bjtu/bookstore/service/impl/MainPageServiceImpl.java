package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.service.MainPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: mainpage service impl
 * @author: CodingLiOOT
 * @create: 2021-07-10 12:21
 * @version: 1.0
 **/
@Service
@Slf4j
public class MainPageServiceImpl implements MainPageService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getNewBooks() {
        return bookMapper.getNewBooks();
    }

    @Override
    public List<Book> getTopBooks() {
        return bookMapper.getTopBooks();
    }

    @Override
    public List<Book> getRecommendedBooks() {

        //推荐待做

        return bookMapper.getTopBooks();
    }
}
