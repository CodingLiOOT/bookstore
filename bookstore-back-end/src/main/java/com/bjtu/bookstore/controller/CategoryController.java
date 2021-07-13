package com.bjtu.bookstore.controller;

import com.bjtu.bookstore.entity.Category;
import com.bjtu.bookstore.service.CategoryService;
import com.bjtu.bookstore.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: category controller
 * @author: CodingLiOOT
 * @create: 2021-07-10 13:31
 * @version: 1.0
 **/
@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/getCategories")
    public List<Category> getCategories() {

        return categoryService.getCategories();
    }
}
