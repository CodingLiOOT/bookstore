package com.bjtu.bookstore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: bookstore-back-end
 * @description: category entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:47
 * @version: 1.0
 **/
@Data
public class Category {

    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private List<Category> childCategory;

    public void addItem(Category c) {
        if (this.childCategory == null) {
            this.childCategory = new ArrayList<>();
        }
        this.childCategory.add(c);
    }
}
