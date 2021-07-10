package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.service.BookService;
import com.bjtu.bookstore.service.MailService;
import com.bjtu.bookstore.service.UserService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/getDetail")
    public HashMap<String, Object> getDetail(@RequestBody Book book) {
        Preconditions.checkNotNull(book.getId());

        return bookService.getDetail(book);
    }
}
