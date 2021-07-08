package com.bjtu.bookstore.entity;

import lombok.Data;

/**
 * @program: bookstore-back-end
 * @description: category entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:47
 * @version: 1.0
 **/
@Data
public class Category {

    private String id;

    private String name;

    private String desc;

    private String parentId;
}
