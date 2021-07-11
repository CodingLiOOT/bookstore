package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.Book;

import java.util.List;

public interface MainPageService {
    List<Book> getNewBooks();

    List<Book> getTopBooks();

    List<Book> getRecommendedBooks();
}
