package com.bjtu.bookstore.service.impl;

import com.bjtu.bookstore.entity.Category;
import com.bjtu.bookstore.mapper.CategoryMapper;
import com.bjtu.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: bookstore-back-end
 * @description: category service impl
 * @author: CodingLiOOT
 * @create: 2021-07-10 13:44
 * @version: 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryMapper.getCategories();

        Map<Integer, Category> parent = categories.stream()
                .filter(category -> null == category.getParentId())
                .collect(Collectors.toMap(Category::getId, a -> a));

        for (Category c : categories) {
            if (!parent.containsKey(c.getId())) {
                parent.get(c.getParentId()).addItem(c);
            }
        }
        return new ArrayList<>(parent.values());
    }
}
