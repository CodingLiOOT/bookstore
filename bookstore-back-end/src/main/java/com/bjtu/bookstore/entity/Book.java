package com.bjtu.bookstore.entity;

import lombok.Data;

/**
 * @program: bookstore-back-end
 * @description: book entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:43
 * @version: 1.0
 **/
@Data
public class Book {
    private String id;

    private String imgUrl;

    private String storeId;

    private String storeName;

    private Integer inventory;

    private String publisher;

    private String name;

    // 能用是1，不能用是0
    private Integer state;

    private Double price;

    private Integer dealNum;

    private String categoryId;

    private String categoryName;

    private int num;

    private int startNum;

    private String commentId;

    private String comment;

    private int rate;
}
