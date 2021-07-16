package com.bjtu.bookstore.service;

import com.bjtu.bookstore.entity.BehaviorItem;
import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;

import java.util.HashMap;

public interface BookService {
    public HashMap<String, Object> getDetail(BehaviorItem myRecommendedItem);

    public HashMap<String, Object> getAllBooks(Cart cart);

    public HashMap<String, Object> search(Cart cart);

    public void shaixuan(Cart cart);

    public HashMap<String, Object> getRightBooks(Book book);

    public void changeBookState(Book book);
}
