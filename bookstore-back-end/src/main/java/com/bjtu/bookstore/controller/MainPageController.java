package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.service.MainPageService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: book controller
 * @author: CodingLiOOT
 * @create: 2021-07-09 15:21
 * @version: 1.0
 **/
@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/mainPage")
public class MainPageController {

    @Autowired
    private MainPageService mainPageService;

    @GetMapping(value = "/getNewBooks")
    public List<Book> getNewBooks() {
        return mainPageService.getNewBooks();
    }

    @GetMapping(value = "/getTopBooks")
    public List<Book> getTopBooks() {
        return mainPageService.getTopBooks();
    }

    @PostMapping(value = "/getRecommendedBooks")
    public List<Book> getRecommendedBooks(@RequestBody User user) {
        return mainPageService.getRecommendedBooks();
    }

}
