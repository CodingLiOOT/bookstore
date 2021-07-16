package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Book;
import com.bjtu.bookstore.entity.User;
import com.bjtu.bookstore.mapper.BookMapper;
import com.bjtu.bookstore.mapper.CategoryMapper;
import com.bjtu.bookstore.mapper.StoreMapper;
import com.bjtu.bookstore.service.MainPageService;
import com.bjtu.bookstore.utils.recommended.MyRecommender;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private MyRecommender myRecommender;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StoreMapper storeMapper;

    @GetMapping(value = "/getNewBooks")
    public List<Book> getNewBooks() {
        return mainPageService.getNewBooks();
    }

    @GetMapping(value = "/getTopBooks")
    public List<Book> getTopBooks() {
        return mainPageService.getTopBooks();
    }

    @PostMapping(value = "/getRecommendedBooks")
    public List<Book> getRecommendedBooks(@RequestBody User user) throws Exception {
//        return mainPageService.getRecommendedBooks();
//        List<Long> items = myRecommender.getRecommendForItem(user.getId(), 515136298);
//        List<Book> books = new ArrayList<>();
//        items.forEach(i -> {
//            Book book = bookMapper.getDetail(i).get(0);
//            bookMapper.addUserBookClick(user.getId(), book.getId());
//            books.add(book);
//        });
//        return books;
        List<String> categoryIds = bookMapper.getMaxCategory(user.getId());
        if (categoryIds.size() == 0) return mainPageService.getTopBooks();
        ArrayList<Book> books = bookMapper.getTopFive(categoryIds.get(0));
        for (int i = 0; i < books.size(); i++) {
            books.get(i).setStoreName(storeMapper.getNameById(books.get(i).getStoreId()));
            books.get(i).setCategoryName(categoryMapper.getNameById(categoryIds.get(0)));
        }
        return books;
    }

    @PostMapping(value = "/getCloud")
    public HashMap<String, Object> getTags(@RequestBody User user) throws Exception {
        List<Book> books;
        if (user != null) {
            books = getRecommendedBooks(user);
            if (books.size() < 5)
                books = bookMapper.getTopBooks();
        } else
            books = bookMapper.getTopBooks();

        ArrayList<String> tagList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            String[] s1 = books.get(i).getName().split("\\s+");
            for (String str : s1)
                tagList.add(str);
        }
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("tagList", tagList);
        return datas;
    }
}
