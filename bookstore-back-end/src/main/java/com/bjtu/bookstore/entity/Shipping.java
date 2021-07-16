package com.bjtu.bookstore.entity;

import lombok.Data;

/**
 * @program: bookstore-back-end
 * @description: shipping entity
 * @author: CodingLiOOT
 * @create: 2021-07-08 15:52
 * @version: 1.0
 **/
@Data
public class Shipping {
    private Long userId;

    private String consigneeName;

    private String address;

    private String phone;

    private String id;
}
