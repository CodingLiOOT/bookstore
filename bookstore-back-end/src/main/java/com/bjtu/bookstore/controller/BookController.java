package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.BehaviorItem;
import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.Cart;
import com.bjtu.bookstore.service.BookService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/getDetail")
    public HashMap<String, Object> getDetail(@RequestBody BehaviorItem myRecommendedItem) {
//        Preconditions.checkNotNull(myRecommendedItem.getUserId());

        return bookService.getDetail(myRecommendedItem);
    }

    @PostMapping(value = "/getAllBooksByCategory")
    public HashMap<String, Object> getAllBooks(@RequestBody Cart cart) {
        Preconditions.checkNotNull(cart.getCartId());

        return bookService.getAllBooks(cart);
    }

    @PostMapping(value = "/search")
    public HashMap<String, Object> search(@RequestBody Cart cart) {
        Preconditions.checkNotNull(cart.getBooks());

        return bookService.search(cart);
    }

    @PostMapping(value = "/shaixuan")
    public void shaixuan(@RequestBody Cart cart) {
        Preconditions.checkNotNull(cart);

        bookService.shaixuan(cart);
    }

    @PostMapping(value = "/getRightBooks")
    public HashMap<String, Object> getRightBooks(@RequestBody Book book) {
        Preconditions.checkNotNull(book);

        return bookService.getRightBooks(book);
    }

    @PostMapping(value = "/changeBookState")
    public void changeState(@RequestBody Book book) {
        Preconditions.checkNotNull(book);

        bookService.changeBookState(book);
    }
}
